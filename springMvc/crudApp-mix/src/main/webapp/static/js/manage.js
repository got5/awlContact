var viewsManager=
(
	function($){
		
		var Module= function(){
			
			var conf={
				menuSelector:'.menu', 
				contentSelector: '#content'
			};
			
				
			var updateContent= function(html){
				$(conf.contentSelector).html(html);
			};
			
			this.makeMenuAjaxable= function(){
				
				$(conf.menuSelector + ' li a')
				.on('click', function(e){
					
					var element= $(this);
					
					element.parent().removeClass('special-element');
					var url= element.attr('href');
					e.preventDefault();
					$.ajax({
						method:'GET',
						url:url,
						cache:false
					})
					.done(function(data){
						element.parent().addClass('special-element');
						updateContent(data);
					});
				})
				.parent()
				.removeClass('special-element');				

				return this;
			};
			
			this.updateView= function(url){
				
				$.ajax({
					method:'GET',
					url:url,
					cache:false
				})
				.done(updateContent);			

				return this;
			};
			
			this.makeFormAjaxable= function(formSelector){
				
				var form= $(formSelector); 
				
				var actionUrl= form.attr('action');
				form.on('submit', function(e){
					e.preventDefault();
					$.post( actionUrl, form.serialize() )
					.done(function(data){
						updateContent(data);
					});
				});
				
				return this;
			};

			
			this.config= function(menuSelector, contentSelector){
				conf.menuSelector= menuSelector;
				conf.contentSelector= contentSelector;
				return this;
			};

		}
		
		
		return new Module();	
	}

)(jQuery)




