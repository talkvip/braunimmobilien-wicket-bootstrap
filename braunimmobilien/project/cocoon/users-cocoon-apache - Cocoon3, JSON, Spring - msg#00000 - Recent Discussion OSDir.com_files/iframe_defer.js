/**
 * Defer iframe loading.
 *
 * Markup: 
 * <div class="defer-iframe" data-src="{SOURCE URL}" data-{ATTR}="{VAL}"></div>
 * https://gist.github.com/bjmiller121/8afeaa49941505af1f1b/c77bb1d0b103531f4b3962a3975006e47e50f48c
 */
$(window).load( function(){
  if ($('.defer-iframe').length) {
    $('.defer-iframe').each( function() {
      var $iframe = $('<iframe frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>');
      $iframe.attr($(this).data());
      $(this).append($iframe);
    });
  }
});
