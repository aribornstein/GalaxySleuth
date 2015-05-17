import java.io.*;

/**
 * Provides the envelope used to serialize objects across a TCP connection and
 * to identify the type of the object upon receipt by the receiving socket
 * connection.
 * 
 * @author Thomas Kelliher kelliher@goucher.edu
 * @version 2010.1018
 */
public class Envelope implements Serializable {

    private static final long serialVersionUID = -5955267069188209476L;
    /**
     * Used to identify the actual type of the object encapsulated by the
     * envelope as msg. Given the type, we can down-cast msg to its appropriate
     * class.
     */
    private MsgType msgType;
    /**
     * The actual object being sent across the connection.
     */
    private Object msg;

    public Envelope(MsgType msgType, Object msg) {
	this.msgType = msgType;
	this.msg = msg;
    }

    public MsgType getMsgType() {
	return msgType;
    }

    public Object getMsg() {
	return msg;
    }
    
    public void setMsgType(MsgType t) {
    	this.msgType = t;
    }
    
    public void setObject(Object obj){
    	this.msg = obj;
    }

}
