package algorithm.queue;

public class Main {
    public static void main(String[] args) {
        CircleQueue<Integer> cq = new CircleQueue();
        for (int i = 0; i < 15; i++) {
            System.out.print("-offer_" + i + "-");
            cq.offer(i);
        }
        System.out.println();
        for (int i = 4; i > 0; i--) {
            Integer poll = cq.poll();
            System.out.print("-poll_" + poll + "-");
        }
        System.out.println();
        System.out.println(cq.toString());
        for (int i = 20; i < 24; i++) {
            System.out.print("-offer_" + i + "-");
            cq.offer(i);
        }
        System.out.println();
        System.out.println("---------------------");
        System.out.println(cq.toString());
//        for (int i = 15; i > 0; i--) {
//            Integer poll = cq.poll();
//            System.out.print("-" + poll + "-");
//        }

        for (int i =100; i < 105; i++) {
            System.out.print("-offer_"  + i + "-");
            cq.offer(i);
        }
        System.out.println();
        System.out.println(cq.toString());
        System.out.println();
        while (!cq.isEmpty()){
            System.out.print("-poll_"  + cq.poll() + "-");
        }
        System.out.println();

    }
}
