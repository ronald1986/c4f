Biz.Layout = {};
Biz.Layout.init = function() {
	Biz.Layout.instance = new YAHOO.widget.Layout({
	    units: [
	        {
	            position: 'top',
	            height: 70, 
	            resize: false, 
	            gutter: '5px', 
	            collapse: true, 
	            resize: false,
	            body: 'header',
	            scroll: null,
	            zIndex: 2
	        },
	        {
	            position: 'right',
	            header: 'Right',
	            width: 300,
	            resize: true, 
	            gutter: '5px', 
	            footer: 'Footer', 
	            collapse: true, 
	            scroll: true
	        },
	        {
	            position: 'bottom', 
	            header: 'Bottom', 
	            height: 100, 
	            resize: true, 
	            gutter: '5px', 
	            collapse: true,
	            body: 'footer'
	        },
	        {
	            position: 'left',
	            header: 'Left',
	            width: 200,
	            resize: true,
	            gutter: '5px',
	            collapse: true,
	            close: true,
	            collapseSize: 50,
	            scroll: null,
	            zIndex: 1,
	            body: 'userMenu'
	        },
	        {
	            position: 'center',
	            body: 'conent'
	        }
	    ]
	});

	Biz.Layout.instance.render();
}