;(function($, undefined) {
        
    "use strict"
    
    $.fn.placeholder = function(options) {
            
        if(!this.length) {
                return
        }

        var settings = $.extend( {
            'delay' : 40
        }, options);

        return this.each(function() {
            var that = $(this),
            forText = that.attr('id'),
            text = that.attr('placeholder');
            
            var wrapper = $('<div></div>', {
                    'class' : 'placeholder-overlay'
            });

            var label = $('<label></label>', {
                'text' :  text,
                'for' : forText,
                'class' : 'placeholder-overlay-label'
            });

            that.wrap(wrapper);
            label.insertAfter(that);

            that.on('keydown', function(e) {

                if(label.css('display') == 'block' && e.which != 8) {
                    label.hide();
                } if(!that.val().length && e.which == 8) {
                    label.fadeIn(settings.delay);
                }
            });

            that.on('keyup', function() {
                if( !that.val()) {
                    label.fadeIn(settings.delay);
                }
            });

        });
    };


    $('.placeholder').placeholder();
    $('.placeholder-overlay-label').on('click', function(event) {
        $(this).siblings('input').focus();
    });
    
})(jQuery);