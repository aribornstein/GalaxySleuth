import java.io.Serializable;


public class refutationHolder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271719199577257571L;
   String[] mySuggestion;
   String refutalCard;
public refutationHolder(String[] mySuggestion, String refutalCard) {
	super();
	this.mySuggestion = mySuggestion;
	this.refutalCard = refutalCard;
}
}
