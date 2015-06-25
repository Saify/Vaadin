// JavaScript timer component 
function TimerComponent(element){
	this._time=0;
	this._elem=element;
	this._updateTime();
};
// Starts the timer
TimerComponent.prototype.start = function(){
	if(!this._timer){
		var self=this;
		this._timer=setInterval(function(){return self._tick();},1000);
	}
};
// Stops the timer
TimerComponent.prototype.stop = function(){
	if(this._timer){
		clearInterval(this._timer);
		this._timer=null;
	}
};
// Resets the timer to 0
TimerComponent.prototype.reset = function(){
	this._time=0;
	this._updateTime();
};
// empty for hooking external tick listener
TimerComponent.prototype.onTick = function(){
	// no op
};
// handle tick internally
TimerComponent.prototype._tick = function(){
	this._time++;
	this._updateTime();
	this.onTick(this._time);
};
// handle time label update internally
TimerComponent.prototype._updateTime = function(){
	this._elem.innerHTML="Time elapsed: "+this._time+" seconds";
};
