$(document).ready(function(){

   
  
   
   $('#valitseTyontekijat').click(function(){
       event.preventDefault();
       $('#chosen option').prop('selected', true);
   });
   
   $('.plannable').hover(function(){
      var id = "Task"+this.id;
      
      $('#'+id).show(); 
   });
   
   $('.plannable').mouseleave(function(){
      var id = "Task"+this.id;
      $('#'+id).hide(); 
   });
   
   $('#kaikki').change(function(){
      var check = this.checked;
      if(check){
          $('#kaikkiPatevyydet input').prop('checked',true);
      }else{
          $('#kaikkiPatevyydet input').prop('checked',false);
      }
   });
   
   var date = $('#alkupaiva input').val();
   
   $('#alkupaiva input').attr("min",date);

});