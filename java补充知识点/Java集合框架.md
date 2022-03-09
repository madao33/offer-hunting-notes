# Java集合框架

## 1 集合概述

<img src="C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220228210110310.png" alt="image-20220228210110310"  />

![image-20220228210120538](C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220228210120538.png)

从上图可以看出，JAVA中以`Map`结尾的类实现了`Map`接口。其他的类都实现了`Collection`接口。

## 1.1 Collection接口和Iterable接口

![image-20220228210905621](C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220228210905621.png)

Collection接口是Iterable接口的子接口，其中重要的方法是返回迭代器的方法。

### 1.1.1 迭代器

#### 1.1.1.1 迭代器源代码

![image-20220228211145864](C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220228211145864.png)

~~~java
//迭代器接口源码
//hasNext方法  还有下一个元素返回true，否则返回false
//next方法     返回下一个元素
public interface Iterator<E> {
    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    E next();

    /**
     * Removes from the underlying collection the last element returned
     * by this iterator (optional operation).  This method can be called
     * only once per call to {@link #next}.  The behavior of an iterator
     * is unspecified if the underlying collection is modified while the
     * iteration is in progress in any way other than by calling this
     * method.
     *
     * @implSpec
     * The default implementation throws an instance of
     * {@link UnsupportedOperationException} and performs no other action.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *         operation is not supported by this iterator
     *
     * @throws IllegalStateException if the {@code next} method has not
     *         yet been called, or the {@code remove} method has already
     *         been called after the last call to the {@code next}
     *         method
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * Performs the given action for each remaining element until all elements
     * have been processed or the action throws an exception.  Actions are
     * performed in the order of iteration, if that order is specified.
     * Exceptions thrown by the action are relayed to the caller.
     *
     * @implSpec
     * <p>The default implementation behaves as if:
     * <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @since 1.8
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
~~~

`Iterator` 对象称为迭代器（设计模式的一种），迭代器可以对集合进行遍历，但每一个集合内部的数据结构可能是不尽相同的，所以每一个集合存和取都很可能是不一样的，虽然我们可以人为地在每一个类中定义 `hasNext()` 和 `next()` 方法，但这样做会让整个集合体系过于臃肿。于是就有了迭代器。

迭代器是将这样的方法抽取出接口，然后在每个类的内部，定义自己迭代方式，这样做就规定了整个集合体系的遍历方式都是 `hasNext()`和`next()`方法，使用者不用管怎么实现的，会用即可。迭代器的定义为：**提供一种方法访问一个容器对象中各个元素，而又不需要暴露该对象的内部细节。**



#### 1.1.1.2迭代器的用处

`Iterator` 主要是用来遍历集合用的，它的特点是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，就会抛出 `ConcurrentModificationException` 异常。



#### 1.1.1.3 Iterator的使用

~~~java
//Map的遍历
Map<Integer, String> map = new HashMap();
map.put(1, "Java");
map.put(2, "C++");
map.put(3, "PHP");
Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
while (iterator.hasNext()) {
  Map.Entry<Integer, String> entry = iterator.next();
  System.out.println(entry.getKey() + entry.getValue());
}

for(Map.Entry<Integer,String> c : map.entrySet()){
    String mapKey = entry.getKey();
    String mapValue = entry.getValue();
    System.out.println(mapKey + "：" + mapValue);
}

/*****************
Iterator 接口的 remove 方法将会删除上次调用 next 方法时返回的元素。在大多数情况
下，在决定删除某个元素之前应该先看一下这个元素是很具有实际意义的。然而， 如果想要
删除指定位置上的元素， 仍然需要越过这个元素。
*****************/
~~~

### 1.1.2 Collection接口

在该接口中最重要的方法为

~~~java
//add方法用于向集合添加元素
//iterator方法用于返回一个实现了 Iterator 接口的对象。
/**
 * @param <E> the type of elements in this collection
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see     Set
 * @see     List
 * @see     Map
 * @see     SortedSet
 * @see     SortedMap
 * @see     HashSet
 * @see     TreeSet
 * @see     ArrayList
 * @see     LinkedList
 * @see     Vector
 * @see     Collections
 * @see     Arrays
 * @see     AbstractCollection
 * @since 1.2
 */

public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    <T> T[] toArray(T[] a);

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    boolean removeAll(Collection<?> c);

    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    boolean retainAll(Collection<?> c);
  
    void clear();
    
    boolean equals(Object o);
    ......
}


~~~



![image-20220228210738889](C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220228210738889.png)

## 1.2 不同集合接口与实现类

### 1.2.1 不同抽象接口区别 List 、Set 、 Map

* `List`接口 存储的元素是有序的、可重复的
* `Set`接口 存储的元素是无序的、不可重复的，类似于数学中集合的概念
* `Map`(用 Key 来搜索的专家): 使用键值对（kye-value）存储，类似于数学上的函数 y=f(x)，“x”代表 key，"y"代表 value，**Key 是无序的、不可重复的**，**value 是无序的、可重复的**，每个键最多映射到一个值。

### 1.2.2 链表List接口

`list`接口的实现类有：

* `ArrayList`    ：   底层实现使用  `Object[]`数组，线程不安全。
* `Vector` ：     底层实现使用  `Object[]`数组，线程安全。子类为Stack，
* `LinkedList`： 双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环)，

#### 1.2.2.1 Arraylist 与 LinkedList 区别?

1. **是否保证线程安全：** `ArrayList` 和 `LinkedList` 都是不同步的，也就是不保证线程安全；

2. **底层数据结构：** `Arraylist` 底层使用的是 **`Object` 数组**；`LinkedList` 底层使用的是 **双向链表** 数据结构（JDK1.6 之前为循环链表，JDK1.7 取消了循环。注意双向链表和双向循环链表的区别，下面有介绍到！）

3. **插入和删除是否受元素位置的影响：**

    ① **`ArrayList` 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。** 比如：执行`add(E e)`方法的时候， `ArrayList` 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是 O(1)。但是如果要在指定位置 i 插入和删除元素的话（`add(int index, E element)`）时间复杂度就为 O(n-i)。因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的(n-i)个元素都要执行向后位/向前移一位的操作。

    ② **`LinkedList` 采用链表存储，所以对于`add(E e)`方法的插入，删除元素时间复杂度不受元素位置的影响，近似 O(1)，如果是要在指定位置`i`插入和删除元素的话（`(add(int index, E element)`） 时间复杂度近似为`o(n))`因为需要先移动到指定位置再插入。**

4. **是否支持快速随机访问：** 

   `LinkedList` 不支持高效的随机元素访问，而 `ArrayList` 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于`get(int index)`方法)。

5. **内存空间占用：** ArrayList 的空 间浪费主要体现在在 list 列表的结尾会预留一定的容量空间，而 LinkedList 的空间花费则体现在它的每一个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。

**总结：** 都是线程不安全的，底层数据结构不同，一个是数组，一个是双向链表。ArrayList插入和删除元素的时间复杂度受元素位置的影响，LinkedList插入的时间复杂度不受元素位置影响，删除元素的时间复杂度受元素位置的影响`ArrayList` 支持随机访问，链表不支持。单个的 LinkedList 的空间花费较大。

**注意：**LinkedList 类还是提供了一个用来访问某个特定元素的 get 方法：  

~~~java
//该方法效率不高
Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
~~~

#### 1.2.2.2  ArrayList的常用方法

1. 增加元素：

~~~java
boolean add(Element e) //增加指定元素到链表尾部.
void add(int index, Element e) //增加指定元素到链表指定位置.
boolean addAll(Collection<? extends E> c) //将指定collection中的所有元素插入到ArrayList中
boolean addAll(int index, Collection<? extends E> c) //从指定的位置开始，将指定collection 中的所有元素插入到ArrayList中

~~~



| 返回值  | 函数名与参数                                  | 描述                                                         |
| ------- | --------------------------------------------- | ------------------------------------------------------------ |
| boolean | add(E e)                                      | 在列表的最后追加元素                                         |
| void    | add(int index, E element)                     | 在列表中指定的位置上插入指定的元素。                         |
| boolean | addAll(Collection<? extends E> c)             | 追加指定集合的所有元素到这个列表的末尾，按他们的指定集合的迭代器返回。 |
| boolean | addAll(int index,  Collection<? extends E> c) | 将指定集合中的所有元素插入到该列表中，从指定位置开始。       |
|         |                                               |                                                              |

2. 删除元素：

~~~java
void clear() //从链表中删除所有元素.
E remove(int index) //删除链表中指定位置的元素.
boolean removeIf(Predicate<? super E> filter) //根据重写Predicate类的test方法选择删除集合中的元素
boolean removeAll(Collection<?> c) //移除ArrayList中Collection所包含的所有元素
boolean remove(Object o) //移除ArrayList中首次出现的指定元素（如果存在则移除并返回true，否则返回false）

~~~



| 返回值           | 函数名与参数                               | 描述                                                         |
| ---------------- | ------------------------------------------ | ------------------------------------------------------------ |
| `E`              | `remove(int index)`                        | 移除此列表中指定位置的元素。                                 |
| boolean          | `remove(Object o)`                         | 从该列表中移除指定元素的第一个发生，如果它是存在的           |
| `boolean`        | `removeAll(Collection<?> c)`               | 从这个列表中移除包含在指定集合中的所有元素。                 |
| `boolean`        | `removeIf(Predicate<? super E> filter)`    | 删除满足给定谓词的这个集合的所有元素。                       |
| `protected void` | `removeRange(int fromIndex,  int toIndex)` | 从列表中删除所有的元素的索引 `fromIndex`之间，包容性，和  `toIndex`，独家。 |
|                  |                                            |                                                              |

3. 获取元素

~~~java
E get(int index) //获取链表中指定位置处的元素.
Object[] toArray() //获取一个数组，数组中所有元素是链表中的元素.（即将链表转换为一个数组）
<T> T[] toArray(T[] a) //构造一个数组
List<E> subList(int fromIndex, int toIndex) //获取从fromIndex到toIndex位置的元素
~~~



| 返回值    | 函数名与参数                        | 描述                                                         |
| --------- | ----------------------------------- | ------------------------------------------------------------ |
| `E`       | get(int index)                      | 获取链表中指定位置处的元素.                                  |
| Object[]  | toArray()                           | 获取一个数组，数组中所有元素是链表中的元素.（即将链表转换为一个数组） |
| `<T> T[]` | toArray(T[] a)                      | 构造一个数组                                                 |
| `List<E>` | subList(int fromIndex, int toIndex) | 获取从fromIndex到toIndex位置的元素                           |

4. 修改元素

~~~java
E set(int index, E element) //将链表中指定位置上的元素替换成新元素。
~~~

5. 搜索元素

~~~java
boolean contains(Object o) //如果链表包含指定元素，返回true.
int indexOf(Object o) //返回元素在链表中第一次出现的位置，如果返回-1，表示链表中没有这个元素。
int lastIndexOf(Object o) //返回元素在链表中最后一次出现的位置，如果返回-1，表示链表中没有这个元素。
~~~

6. 判断链表是否为空

```java
 boolean isEmpty() //返回true表示链表中没有任何元素. 判断逻辑是size == 0
```

7. 获取链表长度

```java
 int size() //返回链表长度（链表包含元素的个数）.
```

**遍历**

~~~java
public class ArrayListDemo {
    public static void main(String args[]){
        List<String> list = new ArrayList<String>();
        list.add("luojiahui");
        list.add("luojiafeng");

        //方法1
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
            System.out.println(it1.next());
        }

        //方法2
        for(Iterator it2 = list.iterator();it2.hasNext();){
             System.out.println(it2.next());
        }

        //方法3
        for(String tmp:list){
            System.out.println(tmp);
        }

        //方法4
        for(int i = 0;i < list.size(); i ++){
            System.out.println(list.get(i));
        }

    }
}
~~~



[ArrayList源码](ArrayList源码.md)

#### 1.2.2.3 ArrayList扩容机制

##### 1 ArrayList的构造函数

~~~java
 /**
     * 默认初始容量大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空数组（用于空实例）。
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

     //用于默认大小空实例的共享空数组实例。
     //我们把它从EMPTY_ELEMENTDATA数组中区分出来，以知道在添加第一个元素时容量需要增加多少。
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     *默认构造函数，使用初始容量10构造一个空列表(无参数构造)
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 带初始容量参数的构造函数。（用户自己指定容量）
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {//初始容量大于0
            //创建initialCapacity大小的数组
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {//初始容量等于0
            //创建空数组
            this.elementData = EMPTY_ELEMENTDATA;
        } else {//初始容量小于0，抛出异常
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }


   /**
    *构造包含指定collection元素的列表，这些元素利用该集合的迭代器按顺序返回
    *如果指定的集合为null，throws NullPointerException。
    */
     public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
~~~



**注意：以无参数构造方法创建 ArrayList 时，实际上初始化赋值的是一个空数组。当真正对数组进行添加元素操作时，才真正分配容量。即向数组中添加第一个元素时，数组容量扩为 10。**

##### 2 add函数的实现

~~~java
/**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     */
/**
     * 将指定的元素追加到此列表的末尾。
     */
    public boolean add(E e) {
        //添加元素之前，先调用ensureCapacityInternal方法，确认有足够的空间
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
~~~

再看看 ensureCapacityInternal函数的实现

~~~java
    
  // 获取默认的容量和传入参数的较大值并返回，确认需要的容量大小
	private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        //如果需要的容量大于现有数组的容量
        if (minCapacity - elementData.length > 0)
            //进行ArrayList扩容
            grow(minCapacity);
    }
~~~

代码运行的分析：

- 当我们要 add 进第 1 个元素到 ArrayList 时，elementData.length 为 0 （因为还是一个空的 list），因为执行了 `ensureCapacityInternal()` 方法 ，所以 minCapacity 此时为 10。此时，`minCapacity - elementData.length > 0`成立，所以会进入 `grow(minCapacity)` 方法。
- 当 add 第 2 个元素时，minCapacity 为 2，此时 e lementData.length(容量)在添加第一个元素后扩容成 10 了。此时，`minCapacity - elementData.length > 0` 不成立，所以不会进入 （执行）`grow(minCapacity)` 方法。
- 添加第 3、4···到第 10 个元素时，依然不会执行 grow 方法，数组容量都为 10。

直到添加第 11 个元素，minCapacity(为 11)比 elementData.length（为 10）要大。进入 grow 方法进行扩容。

##### 3 grow方法的实现

~~~java
	/**
     * 要分配的最大数组大小
     */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    //扩容机制的核心方法
	private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        //新的容量为原来的1.5倍，每次增加原来容量的一半
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        //新容量 一定要大于最小需要容量
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // 如果新容量大于 MAX_ARRAY_SIZE,进入(执行) `hugeCapacity()` 方法       
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        //使用系统方法复制数组到新数组中
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

~~~

##### 4 hugeCapacity方法

如果 minCapacity 大于最大容量，则新容量则为`Integer.MAX_VALUE`，否则，新容量大小则为 MAX_ARRAY_SIZE 即为 `Integer.MAX_VALUE - 8`。

~~~java
   //限制最大的数组大小为 Integer.MAX_VALUE
	private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
~~~

#### 1.2.2.4 System.arraycopy() 和 Arrays.copyOf()方法

##### 1 System.arraycopy()方法

该方法的使用在public void add(int index, E element)中

~~~java
    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        //arraycopy()方法实现数组自己复制自己
        //elementData:源数组;index:源数组中的起始位置;elementData：目标数组；
        //index + 1：目标数组中的起始位置； 
        //size - index：要复制的数组元素的数量；
        
        //作用：将 源数组 的 index位(包括)往后size - index个数据复制到 目标数组
        //从index + 1位开始存放
      //System.arraycopy(源数组,源数组中的起始位置,目标数组,目标数组中的起始位置,复制的数组元素的数量)
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
~~~

##### 2 Arrays.copyOf()方法

使用该 `Arrays.copyOf()`方法主要是为了给原有数组扩容，

~~~java
/**
     以正确的顺序返回一个包含此列表中所有元素的数组（从第一个到最后一个元素）; 返回的数组的运行时类型是指定	  数组的运行时类型。
     */
    public Object[] toArray() {
    	//elementData：要复制的数组；size：要复制的长度
        return Arrays.copyOf(elementData, size);
    }

//一个测试代码
public class ArrayscopyOfTest {

	public static void main(String[] args) {
		int[] a = new int[3];
		a[0] = 0;
		a[1] = 1;
		a[2] = 2;
		int[] b = Arrays.copyOf(a, 10);
		System.out.println("b.length"+b.length);
	}
}

//结果为10
~~~

#### 1.2.2.4 ensureCapacity方法

在需要大量调用add方法添加元素前使用该方法进行扩容可以减少代码运行时间。

~~~java
/**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // any size if not default element table
            ? 0
            // larger than default for default empty table. It's already
            // supposed to be at default size.
            : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }
~~~

#### 1.2.2.5 总结

>ArrayList使用默认构造器时，返回空数组，添加第一个元素时拓展为默认大小10，之后每次自动扩容变为原来的1.5倍。
>
>在需要大量调用add方法添加元素前使用该方法进行扩容可以减少代码运行时间。
>
>主要用到了 System.arraycopy() 和 Arrays.copyOf()方法 进行扩容和添加元素。

### 1.2.3 LinkedList的常用方法

[LinkedList的源码](LinkedList源码.md)

#### 1 增加

~~~java
add(E e)：在链表后添加一个元素；   通用方法
addFirst(E e)：在链表头部插入一个元素；  特有方法
addLast(E e)：在链表尾部添加一个元素；  特有方法
    
push(E e)：与addFirst方法一致  
offer(E e)：在链表尾部插入一个元素                                                      
add(int index, E element)：在指定位置插入一个元素。      
offerFirst(E e)：JDK1.6版本之后，在头部添加； 特有方法
offerLast(E e)：JDK1.6版本之后，在尾部添加； 特有方法

~~~

#### 2  删除

~~~
remove() ：移除链表中第一个元素;    通用方法  
remove(E e)：移除指定元素；   通用方法
removeFirst(E e)：删除头，获取元素并删除；  特有方法
removeLast(E e)：删除尾；  特有方法
pollFirst()：删除头；  特有方法
pollLast()：删除尾；  特有方法
pop()：和removeFirst方法一致，删除头。 
poll()：查询并移除第一个元素     特有方法    
~~~

#### 3 查询

~~~java
get(int index)：按照下标获取元素；  通用方法
getFirst()：获取第一个元素；  特有方法
getLast()：获取最后一个元素； 特有方法
peek()：获取第一个元素，但是不移除；  特有方法
peekFirst()：获取第一个元素，但是不移除； 
peekLast()：获取最后一个元素，但是不移除；
pollFirst()：查询并删除头；  特有方法
pollLast()：删除尾；  特有方法
poll()：查询并移除第一个元素     特有方法

~~~

### 1.2.4 排序比较 ——comparable 和 Comparator 的区别

- `comparable` 接口实际上是出自`java.lang`包 它有一个 `compareTo(Object obj)`方法用来排序
- `comparator`接口实际上是出自 java.util 包它有一个`compare(Object obj1, Object obj2)`方法用来排序

一般我们需要对一个集合使用自定义排序时，我们就要重写`compareTo()`方法或`compare()`方法，当我们需要对某一个集合实现两种排序方式，比如一个 song 对象中的歌名和歌手名分别采用一种排序方法的话，我们可以重写`compareTo()`方法和使用自制的`Comparator`方法或者以两个 Comparator 来实现歌名排序和歌星名排序，第二种代表我们只能使用两个参数版的 `Collections.sort()`.

#### 1 Comparator 定制排序

~~~java
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);
        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):");
        System.out.println(arrayList);

        // void sort(List list),按自然排序的升序排序
        //Integer实现了comparable接口
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);
        // 定制排序的用法
		//使用Comparator编写排序规则
        Collections.sort(arrayList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList);
~~~

Output:

```
原始数组:
[-1, 3, 3, -5, 7, 4, -9, -7]
Collections.reverse(arrayList):
[-7, -9, 4, 7, -5, 3, 3, -1]
Collections.sort(arrayList):
[-9, -7, -5, -1, 3, 3, 4, 7]
定制排序后：
[7, 4, 3, 3, -1, -5, -7, -9]
```

~~~java
// person对象没有实现Comparable接口，所以必须实现，这样才不会出错，才可以使treemap中的数据按顺序排列
// 前面一个例子的String类已经默认实现了Comparable接口，详细可以查看String类的API文档，另外其他
// 像Integer类等都已经实现了Comparable接口，所以不需要另外实现了
public  class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * T重写compareTo方法实现按年龄来排序
     */
    @Override
    public int compareTo(Person o) {
        if (this.age > o.getAge()) {
            return 1;
        }
        if (this.age < o.getAge()) {
            return -1;
        }
        return 0;
    }
}
~~~

### 1.2.5 Set接口

#### 1 无序性和不可重复性

1、什么是无序性？无序性不等于随机性 ，无序性是指存储的数据在底层数组中并非按照数组索引的顺序添加 ，而是根据数据的**哈希值**决定的。

2、什么是不可重复性？不可重复性是指**添加的元素按照 equals()判断时 ，返回 false，**需要同时重写 equals()方法和 HashCode()方法。

#### 2 HashSet、LinkedHashSet 和 TreeSet 

HashSet 是 Set 接口的主要实现类 ，**HashSet 的底层是 HashMap**，线程不安全的，可以存储 null 值；

**LinkedHashSet 是 HashSet 的子类，能够按照添加的顺序遍历**；

TreeSet **底层使用红黑树**，能够按照添加元素的顺序进行遍历，排序的方式有自然排序和定制排序。



**HashSet方法**

![image-20220301204830882](C:\Users\jinhu\AppData\Roaming\Typora\typora-user-images\image-20220301204830882.png)

~~~java

	Set<String> set = new HashSet<String>();

	//迭代遍历
    Iterator<String> it = set.iterator();
    while (it.hasNext()) {
      String str = it.next();
      System.out.println(str);
    }

 	//for循环遍历
    for (String str : set) {
          System.out.println(str);
    }
~~~



### 1.2.6 Map接口

#### 1 HashMap 和 Hashtable 的区别

1. **线程是否安全：** HashMap 是非线程安全的，HashTable 是线程安全的,因为 HashTable 内部的方法基本都经过`synchronized` 修饰。（如果你要保证线程安全的话就使用 ConcurrentHashMap 吧！）；

2. **效率：** 因为线程安全的问题，HashMap 要比 HashTable 效率高一点。另外，HashTable 基本被淘汰，不要在代码中使用它；

3. **对 Null key 和 Null value 的支持：** HashMap 可以存储 null 的 key 和 value，但 null 作为键只能有一个，null 作为值可以有多个；HashTable 不允许有 null 键和 null 值，否则会抛出 NullPointerException。

4. **初始容量大小和每次扩充容量大小的不同 ：** 

   ① 创建时如果不指定容量初始值，Hashtable 默认的初始大小为 11，之后每次扩充，容量变为原来的 2n+1。HashMap 默认的初始化大小为 16。之后每次扩充，容量变为原来的 2 倍。

   ② 创建时如果给定了容量初始值，那么 Hashtable 会直接使用你给定的大小，而 HashMap 会将其扩充为 2 的幂次方大小（HashMap 中的`tableSizeFor()`方法保证，下面给出了源代码）。也就是说 HashMap 总是使用 2 的幂作为哈希表的大小,后面会介绍到为什么是 2 的幂次方。

5. **底层数据结构：** JDK1.8 以后的 HashMap 在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间。Hashtable 没有这样的机制

>- **loadFactor加载因子**
>
>  loadFactor加载因子是控制数组存放数据的疏密程度，loadFactor越趋近于1，那么 数组中存放的数据(entry)也就越多，也就越密，也就是会让链表的长度增加，loadFactor越小，也就是趋近于0，数组中存放的数据(entry)也就越少，也就越稀疏。
>
>  **loadFactor太大导致查找元素效率低，太小导致数组的利用率低，存放的数据会很分散。loadFactor的默认值为0.75f是官方给出的一个比较好的临界值**。
>
>  给定的默认容量为 16，负载因子为 0.75。Map 在使用过程中不断的往里面存放数据，当数量达到了 16 * 0.75 = 12 就需要将当前 16 的容量进行扩容，而扩容这个过程涉及到 rehash、复制数据等操作，所以非常消耗性能。
>
>- **threshold**
>
>  **threshold = capacity \* loadFactor**，**当Size>=threshold**的时候，那么就要考虑对数组的扩增了，也就是说，这个的意思就是 **衡量数组是否需要扩增的一个标准**

**Hashmap类的属性**

```java
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
    // 序列号
    private static final long serialVersionUID = 362498820763181265L;    
    // 默认的初始容量是16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;   
    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30; 
    // 默认的填充因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 当桶(bucket)上的结点数大于这个值时会转成红黑树
    static final int TREEIFY_THRESHOLD = 8; 
    // 当桶(bucket)上的结点数小于这个值时树转链表
    static final int UNTREEIFY_THRESHOLD = 6;
    // 桶中结构转化为红黑树对应的table的最小大小
    static final int MIN_TREEIFY_CAPACITY = 64;
    // 存储元素的数组，总是2的幂次倍
    transient Node<k,v>[] table; 
    // 存放具体元素的集
    transient Set<map.entry<k,v>> entrySet;
    // 存放元素的个数，注意这个不等于数组的长度。
    transient int size;
    // 每次扩容和更改map结构的计数器
    transient int modCount;   
    // 临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
    int threshold;
    // 加载因子
    final float loadFactor;
}


```

~~~java
//initailCapacity*loadFactor=HashMap的容量。
//默认initailCapacity默认大小       16
//默认loadFactor负载因子            0.75
/**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;


/**
     * Returns a power of two size for the given target capacity.
     */
//保证hashmap的大小总为 2 的幂
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
~~~

#### 2 HashMap底层数据分析

##### **JDK1.8之前**

JDK1.8 之前 HashMap 底层是 **数组和链表** 结合在一起使用也就是 **链表散列**。**HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值，然后通过 `(n - 1) & hash` 判断当前元素存放的位置（这里的 n 指的是数组的长度），如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。**

**所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。**

JDK 1.8 的 hash方法 相比于 JDK 1.7 hash 方法更加简化，但是原理不变。

```java
    static final int hash(Object key) {
      int h;
      // key.hashCode()：返回散列值也就是hashcode
      // ^ ：按位异或
      // >>>:无符号右移，忽略符号位，空位都以0补齐
      return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }
```

对比一下 JDK1.7的 HashMap 的 hash 方法源码.

```java
static int hash(int h) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).

    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
```

所谓 **“拉链法”** 就是：将链表和数组相结合。也就是说创建一个链表数组，数组中每一格就是一个链表。若遇到哈希冲突，则将冲突的值加到链表中即可。

![jdk1.8之前的内部结构](https://camo.githubusercontent.com/aa7cb4f75f247d974819c750e8f9ca530a5ee83f1e7b162eebc64a506f38ae92/68747470733a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f323031392d372f6a646b312e382545342542392538422545352538392538442545372539412538342545352538362538352545392538332541382545372542422539332545362539452538342e706e67)



##### JDK1.8之后

![JDK1.8之后的HashMap底层数据结构](https://camo.githubusercontent.com/be08b3aaa0a03eb1252506c9d4811304ffe54b9a9f129caf15f7d824fc007e2b/687474703a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f31382d382d32322f36373233333736342e6a7067)



#### 3 构造函数

```java
// 默认构造函数。
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR; // all   other fields defaulted
 }
 
 // 包含另一个“Map”的构造函数
 public HashMap(Map<? extends K, ? extends V> m) {
     this.loadFactor = DEFAULT_LOAD_FACTOR;
     putMapEntries(m, false);//下面会分析到这个方法
 }
 
 // 指定“容量大小”的构造函数
 public HashMap(int initialCapacity) {
     this(initialCapacity, DEFAULT_LOAD_FACTOR);
 }
 
 // 指定“容量大小”和“加载因子”的构造函数
 public HashMap(int initialCapacity, float loadFactor) {
     if (initialCapacity < 0)
         throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
     if (initialCapacity > MAXIMUM_CAPACITY)
         initialCapacity = MAXIMUM_CAPACITY;
     if (loadFactor <= 0 || Float.isNaN(loadFactor))
         throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
     this.loadFactor = loadFactor;
     this.threshold = tableSizeFor(initialCapacity);
 }
```
#### 4 HashMap的扩容

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 没超过最大值，就扩充为原来的2倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    if (oldTab != null) {
        // 把每个bucket都移动到新的buckets中
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

#### 5 常用方法



```java
package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class HashMapDemo {
public static void main(String[] args) {
    HashMap<String, String> map = new HashMap<String, String>();
    // 键不能重复，值可以重复
    map.put("san", "张三");
    map.put("si", "李四");
    map.put("wu", "王五");
    map.put("wang", "老王");
    map.put("wang", "老王2");// 老王被覆盖
    map.put("lao", "老王");
    System.out.println("-------直接输出hashmap:-------");
    System.out.println(map);
    /**
     * 遍历HashMap
     */
    // 1.获取Map中的所有键
    System.out.println("-------foreach获取Map中所有的键:------");
    Set<String> keys = map.keySet();
    for (String key : keys) {
        System.out.print(key+"  ");
    }
    System.out.println();//换行
    // 2.获取Map中所有值
    System.out.println("-------foreach获取Map中所有的值:------");
    Collection<String> values = map.values();
    for (String value : values) {
        System.out.print(value+"  ");
    }
    System.out.println();//换行
    // 3.得到key的值的同时得到key所对应的值
    System.out.println("-------得到key的值的同时得到key所对应的值:-------");
    Set<String> keys2 = map.keySet();
    for (String key : keys2) {
        System.out.print(key + "：" + map.get(key)+"   ");

    }
    /**
     * 如果既要遍历key又要value，那么建议这种方式，因为如果先获取keySet然后再执行map.get(key)，map内部会执行两次遍历。
     * 一次是在获取keySet的时候，一次是在遍历所有key的时候。
     */
    // 当我调用put(key,value)方法的时候，首先会把key和value封装到
    // Entry这个静态内部类对象中，把Entry对象再添加到数组中，所以我们想获取
    // map中的所有键值对，我们只要获取数组中的所有Entry对象，接下来
    // 调用Entry对象中的getKey()和getValue()方法就能获取键值对了
    Set<java.util.Map.Entry<String, String>> entrys = map.entrySet();
    for (java.util.Map.Entry<String, String> entry : entrys) {
        System.out.println(entry.getKey() + "--" + entry.getValue());
    }
    
    /**
     * HashMap其他常用方法
     */
    System.out.println("after map.size()："+map.size());
    System.out.println("after map.isEmpty()："+map.isEmpty());
    System.out.println(map.remove("san"));
    System.out.println("after map.remove()："+map);
    System.out.println("after map.get(si)："+map.get("si"));
    System.out.println("after map.containsKey(si)："+map.containsKey("si"));
    System.out.println("after containsValue(李四)："+map.containsValue("李四"));
    System.out.println(map.replace("si", "李四2"));
    System.out.println("after map.replace(si, 李四2):"+map);
}
```

}