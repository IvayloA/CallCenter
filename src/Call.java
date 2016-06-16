
public class Call {

	private final int _id;
	private final int _duration;
	private final long _startTime;

	public Call(int id, int duration) {
		assert duration >= 0 : "Call time can't be < 0";
		this._id = id;
		this._duration = duration;
		this._startTime = System.currentTimeMillis();
	}

	public String toString(){
		return "Call " + this._id;
	}
	public void anwer() {
		System.out.println(this + " answered: waited " 
	          + (System.currentTimeMillis() - _startTime) 
	          + " milliseconds");
		
		try {
			Thread.sleep(_duration);
		} catch (InterruptedException e) {
			
		}
	}

}
