import java.beans.Customizer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

public class CallCenterAgent implements Runnable {
	public static final Call GO_HOME = new Call(-1, 0);

	private final int _id;
	private final BlockingQueue<Call> _calls;

	public CallCenterAgent(int id, BlockingQueue<Call> calls) {
		assert calls != null : "calls can't be null";

		this._id = id;
		this._calls = calls;
	}

	public String toString() {
		return "Agent " + this._id;
	}

	public void run() {
		System.out.println(this + " clocked on.");

		while (true) {
			System.out.println(this + " waiting");
			try{
			Call call = this._calls.take();
			System.out.println(this + " answering " + call);

			if (call == GO_HOME) {
				break;
			}
			call.anwer();
			} catch (InterruptedException ex) {
				
			}
		
		}
		System.out.println(this + " go home");
	}
}
