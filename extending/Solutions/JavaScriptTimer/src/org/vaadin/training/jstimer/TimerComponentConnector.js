window.org_vaadin_training_jstimer_TimerComponent = function() {
 // Create the component
 var timer = new TimerComponent(this.getElement());
 // Handle state changes from server
 this.onStateChange = function() {
	 if(this.getState().started){
		 timer.start();
	 } else{
		 timer.stop();
	 }
 };

 // called from server
 this.reset = function() {
	 timer.reset();
 };
 
 self=this;
 
 // handle timer ticks
 timer.onTick = function(time){
	 if(time>0 && time%10==0){
		 timer.stop();
		 self.timeout();
	 }
 };
 
};
