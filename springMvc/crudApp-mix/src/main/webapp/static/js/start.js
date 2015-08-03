(
	function($){
		
		$(document).ready(function(){
			
			var getStartPartialUrl= function(){
				var selected= $('#partialToLoad');
				if (selected !== undefined && selected.length > 0){
					return $(selected[0]).text();
				}
			};
			
			viewsManager
			.config('.main-menu', '#container')
			.makeMenuAjaxable();
			
			var defaultPartialUrl= getStartPartialUrl();
			if (defaultPartialUrl){
				viewsManager.updateView(defaultPartialUrl);
			}
			
			
			
		});

	}
)(jQuery)