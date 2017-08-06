package org.rkb.datastructure.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<T> {
	private DoublyLinkedList<T> dataCacheList;
	private Map<T, CacheNode<T>> cacheMap;

	public LRUCache(int cacheSize) {
		dataCacheList = new DoublyLinkedList<T>(cacheSize);
		cacheMap = new HashMap<T, CacheNode<T>>();
	}

	public T accessCacheData(T data) {
		CacheNode<T> dataNode = null;
		if(cacheMap.containsKey(dataNode)) {
			// If data is present in the cache, move the data to the start of list
			dataNode = cacheMap.get(dataNode);
			dataCacheList.moveDataToFront(dataNode);
		}
		else {
			// If the data is not present in the cache, add the data to the cache
			if(dataCacheList.cacheCurrentSize == dataCacheList.cacheSize) { //check if cache is full.
				cacheMap.remove(dataCacheList.tail.data);//remove the data of the tail from the hashmap
				dataCacheList.removeLastDataNode();//remove the last data from list
			}
			dataNode = dataCacheList.addData(data);//this will add the data to the front
			cacheMap.put(data, dataNode);
		}
		return data;
	}
	
	
	public static void main(String[] args) {
        LRUCache<Integer> cache1 = new LRUCache<>(10);
        cache1.accessCacheData(5);
        cache1.printCache();
        cache1.accessCacheData(7);
        cache1.printCache();
        cache1.accessCacheData(15);
        cache1.printCache();
        cache1.accessCacheData(34);
        cache1.printCache();
        cache1.accessCacheData(23);
        cache1.printCache();
        cache1.accessCacheData(21);
        cache1.printCache();
        cache1.accessCacheData(7);
        cache1.printCache();
        cache1.accessCacheData(32);
        cache1.printCache();
        cache1.accessCacheData(34);
        cache1.printCache();
        cache1.accessCacheData(35);
        cache1.printCache();
        cache1.accessCacheData(15);
        cache1.printCache();
        cache1.accessCacheData(37);
        cache1.printCache();
        cache1.accessCacheData(17);
        cache1.printCache();
        cache1.accessCacheData(28);
        cache1.printCache();
        cache1.accessCacheData(16);
        cache1.printCache();
    }

	
    public void printCache() {
    	dataCacheList.printList();
        System.out.println();
    }

	class DoublyLinkedList<T> {
		private final int cacheSize;
		private int cacheCurrentSize;
		private CacheNode<T> head;
		private CacheNode<T> tail;

		private DoublyLinkedList(int size) {
			this.cacheSize = size;
			cacheCurrentSize = 0;
		}

		private void printList() {
		        if(head == null) {
		            return;
		        }
		        CacheNode<T> node = head;
		        while(node != null) {
		            System.out.print(node.data+" ");
		            node = node.next;
		        }
		    }

		private CacheNode<T> addData(T data) {
			CacheNode<T> dataNode = new CacheNode<T>(data);
			if (head == null) { //point the new node as head and tail
				head = dataNode;
				tail = dataNode;
				cacheCurrentSize = 1;
				return dataNode;
			} else if (cacheCurrentSize < cacheSize) { //increase the cache size
				cacheCurrentSize++;
			} 
			//Add the new dataNode before the current head
			head.prev = dataNode;
			dataNode.next = head;
			head = dataNode;
			return dataNode;
		}
		
		public void removeLastDataNode () {
			tail = tail.prev;
			tail.next = null;
		}

		public void moveDataToFront(CacheNode<T> dataNode) {
			if (dataNode == null || head == dataNode) {//if head itself is data node means it is first node
				return;
			}
			// if the datanode is the last element we need to bring front. so set tail to .
			if (dataNode == tail) {
				removeLastDataNode ();
			}

			CacheNode<T> prev = dataNode.prev;
			CacheNode<T> next = dataNode.next;

			prev.next = null;

			if (next != null) {
				next.prev = prev;
			}

			// make the dataNode as the first Node means assign to head.
			dataNode.prev = null;
			dataNode.next = head;
			head.prev = dataNode;
			head = dataNode;
		}
	}

	class CacheNode<T> {
		private T data;
		private CacheNode<T> prev;
		private CacheNode<T> next;
		CacheNode(T data) {
			this.data = data;
		}
	}
}
