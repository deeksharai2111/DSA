import java.util.HashMap;
import java.util.Map;

class LFUCache {

    final int capacity;
    int curSize;
    int minFrequency;

    Map<Integer, DLLNode> cache;
    Map<Integer, DoubleLinkedList> frequencyMap;

    /**
     * @param capacity : total capacity of LFU Cache
     * @param curSize : current size of LFU Cache
     * @param minFrequency : minimum frequency present in cache
     * @param cache : key -> node mapping
     * @param frequencyMap : frequency -> doubly linked list mapping
     */
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;

        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }

    /**
     * Get value by key and update frequency
     */
    public int get(int key) {

        DLLNode curNode = cache.get(key);

        if (curNode == null) {
            return -1;
        }

        updateNode(curNode);

        return curNode.val;
    }

    /**
     * Insert or update key-value pair
     */
    public void put(int key, int value) {

        if (capacity == 0) {
            return;
        }

        // Key already exists
        if (cache.containsKey(key)) {

            DLLNode curNode = cache.get(key);

            curNode.val = value;

            updateNode(curNode);
        }

        // New key
        else {

            curSize++;

            // Cache full -> remove LFU node
            if (curSize > capacity) {

                DoubleLinkedList minFreqList =
                        frequencyMap.get(minFrequency);

                cache.remove(minFreqList.tail.prev.key);

                minFreqList.removeNode(minFreqList.tail.prev);

                curSize--;
            }

            minFrequency = 1;

            DLLNode newNode = new DLLNode(key, value);

            DoubleLinkedList curList =
                    frequencyMap.getOrDefault(
                            1,
                            new DoubleLinkedList());

            curList.addNode(newNode);

            frequencyMap.put(1, curList);

            cache.put(key, newNode);
        }
    }

    /**
     * Update frequency of a node
     */
    public void updateNode(DLLNode curNode) {

        int curFreq = curNode.frequency;

        DoubleLinkedList curList =
                frequencyMap.get(curFreq);

        curList.removeNode(curNode);

        // Update min frequency if needed
        if (curFreq == minFrequency &&
                curList.size == 0) {

            minFrequency++;
        }

        curNode.frequency++;

        DoubleLinkedList newList =
                frequencyMap.getOrDefault(
                        curNode.frequency,
                        new DoubleLinkedList());

        newList.addNode(curNode);

        frequencyMap.put(curNode.frequency, newList);
    }

    /**
     * Node of Doubly Linked List
     */
    class DLLNode {

        int key;
        int val;
        int frequency;

        DLLNode prev;
        DLLNode next;

        public DLLNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.frequency = 1;
        }
    }

    /**
     * Doubly Linked List
     */
    class DoubleLinkedList {

        int size;

        DLLNode head;
        DLLNode tail;

        public DoubleLinkedList() {

            this.size = 0;

            head = new DLLNode(0, 0);
            tail = new DLLNode(0, 0);

            head.next = tail;
            tail.prev = head;
        }

        /**
         * Add node after head
         */
        public void addNode(DLLNode node) {

            DLLNode nextNode = head.next;

            node.next = nextNode;
            node.prev = head;

            head.next = node;
            nextNode.prev = node;

            size++;
        }

        /**
         * Remove node
         */
        public void removeNode(DLLNode node) {

            DLLNode prevNode = node.prev;
            DLLNode nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            size--;
        }
    }
}