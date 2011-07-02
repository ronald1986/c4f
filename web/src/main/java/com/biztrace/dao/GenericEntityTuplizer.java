package com.biztrace.dao;

import java.util.Map;

import org.hibernate.EntityMode;
import org.hibernate.EntityNameResolver;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.proxy.ProxyFactory;
import org.hibernate.proxy.map.MapProxyFactory;
import org.hibernate.tuple.DynamicMapInstantiator;
import org.hibernate.tuple.Instantiator;
import org.hibernate.tuple.entity.AbstractEntityTuplizer;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biztrace.model.GenericEntityImpl;

public class GenericEntityTuplizer extends AbstractEntityTuplizer {
    static final Logger log = LoggerFactory.getLogger(GenericEntityTuplizer.class);

    GenericEntityTuplizer(EntityMetamodel entityMetamodel, PersistentClass mappedEntity) {
        super(entityMetamodel, mappedEntity);
    }

    /**
     * {@inheritDoc}
     */
    public EntityMode getEntityMode() {
        return EntityMode.MAP;
    }

    private PropertyAccessor buildPropertyAccessor(Property mappedProperty) {
        if (mappedProperty.isBackRef()) {
            return mappedProperty.getPropertyAccessor(null);
        } else {
            return PropertyAccessorFactory.getDynamicMapPropertyAccessor();
        }
    }

    /**
     * {@inheritDoc}
     */
    protected Getter buildPropertyGetter(Property mappedProperty, PersistentClass mappedEntity) {
        return buildPropertyAccessor(mappedProperty).getGetter(null, mappedProperty.getName());
    }

    /**
     * {@inheritDoc}
     */
    protected Setter buildPropertySetter(Property mappedProperty, PersistentClass mappedEntity) {
        return buildPropertyAccessor(mappedProperty).getSetter(null, mappedProperty.getName());
    }

    private static final class GenericMapInstantiator extends DynamicMapInstantiator {
        private static final long serialVersionUID = 1L;

        public GenericMapInstantiator(PersistentClass mappingInfo) {
            super(mappingInfo);
        }

        // override the generateMap() method to return our custom map...
        @Override
        protected Map<String, Object> generateMap() {
            return new GenericEntityImpl();
        }
    }

    /**
     * {@inheritDoc}
     */
    protected Instantiator buildInstantiator(PersistentClass mappingInfo) {
        return new GenericMapInstantiator(mappingInfo);
    }

    /**
     * {@inheritDoc}
     */
    protected ProxyFactory buildProxyFactory(PersistentClass mappingInfo, Getter idGetter, Setter idSetter) {
        ProxyFactory pf = new MapProxyFactory();
        try {
            //TODO: design new lifecycle for ProxyFactory
            pf.postInstantiate(getEntityName(), null, null, null, null, null);
        } catch (HibernateException he) {
            log.warn("could not create proxy factory for:" + getEntityName(), he);
            pf = null;
        }
        return pf;
    }

    /**
     * {@inheritDoc}
     */
    public Class getMappedClass() {
        return Map.class;
    }

    /**
     * {@inheritDoc}
     */
    public Class getConcreteProxyClass() {
        return Map.class;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isInstrumented() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public EntityNameResolver[] getEntityNameResolvers() {
        return new EntityNameResolver[] {BasicEntityNameResolver.INSTANCE};
    }

    /**
     * {@inheritDoc}
     */
    public String determineConcreteSubclassEntityName(Object entityInstance, SessionFactoryImplementor factory) {
        return extractEmbeddedEntityName((Map) entityInstance);
    }

    public static String extractEmbeddedEntityName(Map entity) {
        return (String) entity.get(DynamicMapInstantiator.KEY);
    }

    public static class BasicEntityNameResolver implements EntityNameResolver {
        public static final BasicEntityNameResolver INSTANCE = new BasicEntityNameResolver();

        /**
         * {@inheritDoc}
         */
        public String resolveEntityName(Object entity) {
            final String entityName = extractEmbeddedEntityName((Map) entity);
            if (entityName == null) {
                throw new HibernateException( "Could not determine type of dynamic map entity" );
            }
            return entityName;
        }

        /**
         * {@inheritDoc}
         */
        public boolean equals(Object obj) {
            return getClass().equals(obj.getClass());
        }

        /**
         * {@inheritDoc}
         */
        public int hashCode() {
            return getClass().hashCode();
        }
    }
}
