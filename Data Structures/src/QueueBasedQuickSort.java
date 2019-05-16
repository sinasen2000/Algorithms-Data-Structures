import javax.swing.plaf.SliderUI;
import java.util.Comparator;

public class QueueBasedQuickSort {

    public static <K> void quickSort(SLLBasedQueue<K> S, Comparator<K> comp){

        int n = S.size();

        if (n < 2) return;

        K pivot = S.first();

        SLLBasedQueue<K> l = new SLLBasedQueue<>();
        SLLBasedQueue<K> e = new SLLBasedQueue<>();
        SLLBasedQueue<K> g = new SLLBasedQueue<>();

        while(!S.isEmpty()){
            K element = S.dequeue();
            int a = comp.compare(element, pivot);

            if (a > 0){
                g.enqueue(element);
            }else if(a == 0){
                e.enqueue(element);
            }else{
                l.enqueue(element);
            }
        }

        quickSort(l, comp);
        quickSort(g, comp);

        while (!l.isEmpty()){
            S.enqueue(l.dequeue());
        }
        while (!e.isEmpty()){
            S.enqueue(e.dequeue());
        }
        while (!g.isEmpty()){
            S.enqueue(g.dequeue());
        }
    }

}
