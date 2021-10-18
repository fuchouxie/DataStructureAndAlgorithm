package stack;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 动态数组栈
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;
    //栈空判断
    public boolean isEmpty(){
        return N==0;
    }
    //栈的size
    public int size(){
        return N;
    }
    //数组扩容
    private void resize(int max){
        Item[] newArray = (Item[]) new Object[max];
        for (int i = 0;i <= N;i++){
            newArray[i] = this.a[i];
        }
        this.a = newArray;
    }
    //压栈:将元素添加到栈顶
    public void push(Item item){
        if(a.length == N)
            resize(2*a.length);
        a[N++] = item;
    }
    //弹栈:将栈顶元素弹出
    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if (N>0&&N==a.length/4)
            resize(a.length/2);
        return item;
    }
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }
    private class ReverseArrayIterator implements Iterator<Item>{
        private int i = N;
        @Override
        public boolean hasNext() {
            return i>0;
        }
        @Override
        public Item next() {
            return a[--i];
        }
    }
}
