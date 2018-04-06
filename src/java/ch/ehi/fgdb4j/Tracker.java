package ch.ehi.fgdb4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.Map.Entry;
/**
 * class Aclass {
 * protected Aclass() {
 *   Tracker.startObject(this);
 * }
 *
 * public void Close() {
 *   Tracker.endObject(this);
 * }
 * 
 * 
 *       HashMap<StackTraceElement[], String> allocs = Tracker.getInstance().getAllocations();
 *       for(Entry<StackTraceElement[], String> alloc:allocs.entrySet()) {
 *           String className=alloc.getValue();
 *           System.out.println(className+":"+alloc.getKey()[0]);
 *       }
 */
public class Tracker {
    static private Tracker instance=null;
    private WeakHashMap<Object, StackTraceElement[]> objs=new WeakHashMap<Object, StackTraceElement[]>();
    private HashMap<StackTraceElement[],String> allocations=new HashMap<StackTraceElement[],String>();
    private Tracker() {
        
    }
    public static Tracker getInstance() {
        if(instance==null) {
            instance=new Tracker();
        }
        return instance;
    }
    static public void startObject(Object obj) {
        getInstance().start_Object(obj);
    }
    private void start_Object(Object obj) {
        String className=obj.getClass().getName();
        StackTraceElement origin[]=getOrigin();
        objs.put(obj, origin);
        allocations.put(origin, className);
    }
    static public void endObject(Object obj) {
        getInstance().end_Object(obj);
    }
    private void end_Object(Object obj) {
        StackTraceElement origin[]=objs.remove(obj);
        allocations.remove(origin);
    }
    static private StackTraceElement[] getOrigin(){
        Throwable tr=new Throwable();
        StackTraceElement stack[]=tr.getStackTrace();
        // stack[0]: getOrigin()
        // stack[1]: logError()
        // stack[2]: user code
        if(2<stack.length){
            StackTraceElement ret[]=Arrays.copyOfRange(stack, 3,stack.length-3);
            return ret;
        }
        return null;
    }
    public HashMap<StackTraceElement[], String> getAllocations() {
        return allocations;
    }
}
