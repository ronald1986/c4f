GOTO REAL_STATEMENT
mongoimport -v -d local -c menu --drop --file menu.json
mongoimport -v -d local -c org --drop --file org.json
mongoimport -v -d local -c usr --drop --file usr.json
:REAL_STATEMENT
mongoimport -v -d local -c product --drop --file product.json
mongoimport -v -d local -c process --drop --file process.json