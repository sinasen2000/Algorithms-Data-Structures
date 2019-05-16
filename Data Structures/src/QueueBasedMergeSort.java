import java.util.Comparator;


public class QueueBasedMergeSort<K> {

    public static <K> void merge(SLLBasedQueue<K> S1, SLLBasedQueue<K> S2, SLLBasedQueue<K> S, Comparator<K> comp){
        while (!S1.isEmpty() && !S2.isEmpty()){
            if (comp.compare(S1.first(), S2.first()) < 1){
                S.enqueue(S1.dequeue());
            }else{
                S.enqueue(S2.dequeue());
            }
            while (!S1.isEmpty()){
                S.enqueue(S1.dequeue());
            }
            while (!S2.isEmpty()){
                S.enqueue(S1.dequeue());
            }

        }
    }

    public static <K> void mergeSort(SLLBasedQueue<K> S, Comparator<K> comp){

        int n = S.size();
        if (n < 2) return;

        int mid = n / 2;

        SLLBasedQueue<K> S1 = new SLLBasedQueue<>();
        SLLBasedQueue<K> S2 = new SLLBasedQueue<>();

        while (S.size() < mid){
            S1.enqueue(S.dequeue());
        }
        while (!S.isEmpty()){
            S2.enqueue(S.dequeue());
        }

        mergeSort(S1, comp);
        mergeSort(S2, comp);


        merge(S1, S2, S, comp);

    }
}
