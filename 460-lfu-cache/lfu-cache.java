   class LFUCache{
    final int capacity;
    int curSize;
    int minFrequency;
    Map<Integer, DLLNode> cache;
    Map<Integer, DoubleLinkedList> frequencyMap;
    class DLLNode{
        int key;
        int val;
        int frequency;
        DLLNode next;
        DLLNode prev;
        DLLNode(int key, int val){
            this.key = key;
            this.val = val;
            this.frequency = 1;
        }
    }
    class DoubleLinkedList{
       int size;
        DLLNode head;
        DLLNode tail;
        DoubleLinkedList(){
            head = new DLLNode(0, 0);
            tail = new DLLNode(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        void addNode(DLLNode node) {
        DLLNode nextNode = head.next;
            node.next = nextNode;
            node.prev = head;
            head.next = node;
            nextNode.prev = node;
         size++;
        }
      void removeNode(DLLNode node) {
            DLLNode prevNode = node.prev;
            DLLNode nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
        }
    }
      public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
    }
   public int get(int key){
         DLLNode curNode = cache.get(key);
         if(curNode == null){
            return -1;
        }
      updateNode(curNode);
      return curNode.val;
    }
   public void put(int key, int value) {
         if(capacity == 0) {
            return;
        }
       if(cache.containsKey(key)){
             DLLNode curNode = cache.get(key); 
             curNode.val = value;
              updateNode(curNode);
        }
       else {
        curSize++;
       if(curSize > capacity) {
      DoubleLinkedList minFreqList = frequencyMap.get(minFrequency);
      cache.remove(minFreqList.tail.prev.key);
       minFreqList.removeNode( minFreqList.tail.prev);
             curSize--;
            }
        minFrequency = 1;
        DLLNode newNode = new DLLNode(key, value);
        DoubleLinkedList curList = frequencyMap.getOrDefault( 1, new DoubleLinkedList());
        curList.addNode(newNode);
        frequencyMap.put(1, curList);
        cache.put(key, newNode);
        }
    }
        public void updateNode(DLLNode curNode) {
        int curFreq = curNode.frequency;
        DoubleLinkedList curList = frequencyMap.get(curFreq);
        curList.removeNode(curNode);
       if(curFreq == minFrequency && curList.size == 0) {
           minFrequency++;
        }
       curNode.frequency++;
    DoubleLinkedList newList = frequencyMap.getOrDefault(curNode.frequency,new DoubleLinkedList());
    newList.addNode(curNode);
 frequencyMap.put( curNode.frequency, newList);
    }
}