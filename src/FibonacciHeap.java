import java.util.*;

public final class FibonacciHeap<T> {
    public static final class Entry<T> {
        private int     mDegree = 0;       // количество детей
        private boolean mIsMarked = false; // отметка узла
        private Entry<T> mNext;
        private Entry<T> mPrev;
        private Entry<T> mParent; // родитель
        private Entry<T> mChild;  // ребенок
        private T      mElem;     // текущий
        private double mPriority; // приоритет

        public T getValue() {
            return mElem;
        }
        public void setValue(T value) {
            mElem = value;
        }
        public double getPriority() {
            return mPriority;
        }
        //конструктор нового элемента
        private Entry(T elem, double priority) {
            mNext = mPrev = this;
            mElem = elem;
            mPriority = priority;
        }
    }
    //указатель на наименьший элемент
    private Entry<T> mMin = null;
    // размер кучи
    private int mSize = 0;

    //добавить элемент с заданным весом
    public Entry<T> enqueue(T value, double priority) {
        checkPriority(priority);
        Entry<T> result = new Entry<T>(value, priority);
        //тпереь сольем этот лист с остальными в дереве
        mMin = mergeLists(mMin, result);
        ++mSize;
        return result;
    }
    // получаем минимальный элемент
    public Entry<T> min() {
        if (isEmpty())
            throw new NoSuchElementException("А куча-то, пустая!");
        return mMin;
    }
    public boolean isEmpty() {
        return mMin == null;
    }
    public int size() {
        return mSize;
    }
    //Слияние двух куч в одну. Старые зачищаются, хотя остаются доступными, но пустыми.
    public static <T> FibonacciHeap<T> merge(FibonacciHeap<T> one, FibonacciHeap<T> two) {
        FibonacciHeap<T> result = new FibonacciHeap<T>();
        //сольем два корня куч, заодно в силу реализации получим результат уже упорядоченным.
        result.mMin = mergeLists(one.mMin, two.mMin);
        result.mSize = one.mSize + two.mSize;
        one.mSize = two.mSize = 0;
        one.mMin  = null;
        two.mMin  = null;
        return result;
    }

    public Entry<T> dequeueMin() {
        if (isEmpty())
            throw new NoSuchElementException("А куча-то, пустая!");
        --mSize;
        Entry<T> minElem = mMin;
        // Если один элемент на руте, то просто перезаписываем его в нул, а если есть еще, то выписываем соседей.
        if (mMin.mNext == mMin) {
            mMin = null;
        }
        else {
            mMin.mPrev.mNext = mMin.mNext;
            mMin.mNext.mPrev = mMin.mPrev;
            mMin = mMin.mNext;
        }
        //Теперь зачистим родительские поля всех детей
        if (minElem.mChild != null) {
            Entry<?> curr = minElem.mChild;
            do {
                curr.mParent = null;
                //пока не дошли до изначального
                curr = curr.mNext;
            } while (curr != minElem.mChild);
        }
        //собираем лист
        mMin = mergeLists(mMin, minElem.mChild);
        //если записей больше нет, готово
        if (mMin == null) return minElem;
        //собираем деревья
        List<Entry<T>> treeTable = new ArrayList<Entry<T>>();
       //список узлов, которые надо обойти
        List<Entry<T>> toVisit = new ArrayList<Entry<T>>();
       //идем пока не вернемся к уже пройденному
        for (Entry<T> curr = mMin; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.mNext)
            toVisit.add(curr);
        for (Entry<T> curr: toVisit) {
            while (true) {
              while (curr.mDegree >= treeTable.size())
                    treeTable.add(null);
                if (treeTable.get(curr.mDegree) == null) {
                    treeTable.set(curr.mDegree, curr);
                    break;
                }
                Entry<T> other = treeTable.get(curr.mDegree);
                treeTable.set(curr.mDegree, null);
                //получим меньший
                Entry<T> min = (other.mPriority < curr.mPriority)? other : curr;
                Entry<T> max = (other.mPriority < curr.mPriority)? curr  : other;
                //забираем max и кидаем к min дочернего
                max.mNext.mPrev = max.mPrev;
                max.mPrev.mNext = max.mNext;
                max.mNext = max.mPrev = max;
                min.mChild = mergeLists(min.mChild, max);
                max.mParent = min;
                max.mIsMarked = false;
                ++min.mDegree;
                curr = min;
            }
            if (curr.mPriority <= mMin.mPriority) mMin = curr;
        }
        return minElem;
    }
    //увеличение ключа
    public void decreaseKey(Entry<T> entry, double newPriority) {
        checkPriority(newPriority);
        if (newPriority > entry.mPriority)
            throw new IllegalArgumentException("Нужно больше золота!");
        decreaseKeyUnchecked(entry, newPriority);
    }

    public void delete(Entry<T> entry) {
        decreaseKeyUnchecked(entry, Double.NEGATIVE_INFINITY);
        dequeueMin();
    }
    private void checkPriority(double priority) {
        if (Double.isNaN(priority))
            throw new IllegalArgumentException(priority + " запрещено");
    }

    //предполагается, что мержит по указателям на минимальные
    private static <T> Entry<T> mergeLists(Entry<T> one, Entry<T> two) {
        if (one == null && two == null) {
            return null;
        }
        else if (one != null && two == null) {
            return one;
        }
        else if (one == null && two != null) {
            return two;
        }
        else {
            Entry<T> oneNext = one.mNext;
            one.mNext = two.mNext;
            one.mNext.mPrev = one;
            two.mNext = oneNext;
            two.mNext.mPrev = two;
            return one.mPriority < two.mPriority? one : two;
        }
    }
    private void decreaseKeyUnchecked(Entry<T> entry, double priority) {
        entry.mPriority = priority;
        if (entry.mParent != null && entry.mPriority <= entry.mParent.mPriority)
            cutNode(entry);
        if (entry.mPriority <= mMin.mPriority)
            mMin = entry;
    }

    private void cutNode(Entry<T> entry) {
        entry.mIsMarked = false;
        if (entry.mParent == null) return;
        if (entry.mNext != entry) {
            entry.mNext.mPrev = entry.mPrev;
            entry.mPrev.mNext = entry.mNext;
        }

        if (entry.mParent.mChild == entry) {
            if (entry.mNext != entry) {
                entry.mParent.mChild = entry.mNext;
            }
            else {
                entry.mParent.mChild = null;
            }
        }
        --entry.mParent.mDegree;
        entry.mPrev = entry.mNext = entry;
        mMin = mergeLists(mMin, entry);
        if (entry.mParent.mIsMarked)
            cutNode(entry.mParent);
        else
            entry.mParent.mIsMarked = true;
        entry.mParent = null;
    }
}