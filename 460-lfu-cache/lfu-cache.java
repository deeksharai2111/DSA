class LFUCache{
    final int capacity;
    int cursize;
    int minfrequency;
Map<Integer, DLLNode>cache;
Map<Integer, DoubleLinkedList>frequencyMap;
public LFUCache(int capacity){
        this.capacity = capacity;
        this.cursize = 0;
        this.minfrequency = 0;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
}
public int get(int key){
    DLLNode curnode = cache.get(key);
    if (curnode == null){
        return -1;
    }
    updatenode(curnode);
    return curnode.val;
}
public void put(int key, int value){
    if(capacity == 0){
        return;
    }         
if(cache.containsKey(key)){
    DLLNode curnode =  cache.get(key);
    curnode.val = value;
    updatenode(curnode);
}
else{
    cursize++;
    if(cursize > capacity){
    DoubleLinkedList minfreqlist = frequencyMap.get(minfrequency);
    cache.remove(minfreqlist.tail.prev.key);
    minfreqlist.removenode(minfreqlist.tail.prev);
    cursize--;
    }
    minfrequency = 1;
DLLNode newnode = new DLLNode(key,value);
DoubleLinkedList curList = frequencyMap.getOrDefault(1,new DoubleLinkedList());

        curList.addnode(newnode);
        frequencyMap.put(1,curList);
        cache.put(key, newnode);
}
}
public void updatenode(DLLNode curnode){
    int curfreq = curnode.frequency;
DoubleLinkedList curlist = frequencyMap.get(curfreq);
        curlist.removenode(curnode);
    if(curfreq == minfrequency && curlist.size == 0){
            minfrequency++;
        }
    curnode.frequency++;
DoubleLinkedList newList = frequencyMap.getOrDefault(curnode.frequency,new DoubleLinkedList());
newList.addnode(curnode);
frequencyMap.put(curnode.frequency,newList);
    }
class DLLNode{
    int key;
    int val;
    int frequency;
    DLLNode prev;
    DLLNode next;
public DLLNode(int key, int val){
    this.key = key;
    this.val = val;
    this.frequency = 1;
  }  
}
 class DoubleLinkedList{
    int size;
    DLLNode head;
    DLLNode tail;

  public DoubleLinkedList(){
    this.size = 0;
    head = new DLLNode(0,0);
    tail = new DLLNode(0,0);
  head.next = tail;
  tail.prev = head;
  }
  public void addnode(DLLNode node){
  DLLNode nextnode = head.next;
          node.next = nextnode;
          node.prev= head;
          head.next = node;

          nextnode.prev = node;
          size++;
}
public void removenode(DLLNode node){
    DLLNode prevnode = node.prev;
     DLLNode nextNode = node.next;
            prevnode.next = nextNode;
            nextNode.prev = prevnode;
            size--;
 }
}
}
