package etc;

import java.util.Vector;

/**
 * Created by mccccopley on 2/1/2017.
 */
public class Heap {
    public Vector<Integer> heapValues;

    public Heap() {
        heapValues = new Vector<>();
    }

    public boolean isHeap() {
        for (int index = heapValues.size() - 1; index > 0; --index) {
            int parentIndex = getParentIndex(index);
            if (heapValues.get(parentIndex) > heapValues.get(index)) {
                return false;
            }
        }
        return true;
    }

    public int getParentIndex(int index) {
        return ((index + 1) / 2) - 1;
    }

    public int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    public int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    public void bubbleUpIndex(int index) {
        while (index != 0) {
            int parentIndex = getParentIndex(index);
            if (heapValues.get(parentIndex) > heapValues.get(index)) {
                int tmp = heapValues.get(parentIndex);
                heapValues.set(parentIndex, heapValues.get(index));
                heapValues.set(index, tmp);
                index = parentIndex;
            }
        }
    }

    public void filterDownIndex(int index) {
        while (true) {
            int childIndex = index;
            int leftIndex = getLeftChildIndex(index);
            if (leftIndex < heapValues.size() && heapValues.get(leftIndex) < heapValues.get(childIndex)) {
                childIndex = leftIndex;
            }
            int rightIndex = getRightChildIndex(index);
            if (rightIndex < heapValues.size() && heapValues.get(rightIndex) < heapValues.get(childIndex)) {
                childIndex = rightIndex;
            }
            if (childIndex != index) {
                int tmp = heapValues.get(childIndex);
                heapValues.set(childIndex, heapValues.get(index));
                heapValues.set(index, tmp);
                index = childIndex;
            } else {
                break;
            }
        }
    }

    public int getValueIndex(int index, int value) {
        return heapValues.indexOf(value);
    }

    public void removeIndex(int index) {
        int lastIndex = heapValues.size() - 1;
        int lastValue = heapValues.remove(lastIndex);
        if (index != lastIndex) {
            heapValues.set(index, lastValue);
            if (index != 0) {
                int parentIndex = getParentIndex(index);
                if (heapValues.get(parentIndex) > lastValue) {
                    bubbleUpIndex(index);
                } else {
                    filterDownIndex(index);
                }
            } else {
                filterDownIndex(index);
            }
        }
    }

    public void addToHeap(int value) {
        int index = heapValues.size();
        heapValues.add(value);
        bubbleUpIndex(index);
    }

    public void removeFromHeap(int value) {
        int valueIndex = getValueIndex(0, value);
        removeIndex(valueIndex);
    }

    public void printMin() {
        System.out.println(heapValues.get(0));
    }
}
