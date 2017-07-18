### 生产者/消费者的多种Java实现方式

对于多线程程序来说，不管任何编程语言，生产者和消费者模型都是最经典的。
就像学习每一门编程语言一样，Hello World！都是最经典的例子。

实际上，准确说应该是“生产者-消费者-仓储”模型，离开了仓储，生产者消费者模型就显得没有说服力了。
对于此模型，应该明确一下几点：
> 1、生产者仅仅在仓储未满时候生产，仓满则停止生产。

> 2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。

> 3、当消费者发现仓储没产品可消费时候会通知生产者生产。[调用notifyAll()]

> 4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费。[调用wait()]

生产者消费者问题是研究多线程程序时绕不开的经典问题之一，
它描述是有一块缓冲区作为仓库，生产者可以将产品放入仓库，消费者则可以从仓库中取走产品。
解决生产者/消费者问题的方法可分为两类：
> （1）采用某种机制保护生产者和消费者之间的同步；

> （2）在生产者和消费者之间建立一个管道。第一种方式有较高的效率，并且易于实现，代码的可控制性较好，属于常用的模式。

> 注:第二种管道缓冲区不易控制，被传输数据对象不易于封装等，实用性不强。因此本文只介绍同步机制实现的生产者/消费者问题。

同步问题核心在于：如何保证同一资源被多个线程并发访问时的完整性。
常用的同步方法是采用信号或加锁机制，保证资源在任意时刻至多被一个线程访问。
Java语言在多线程编程上实现了完全对象化，提供了对同步机制的良好支持。
在Java中一共有四种方法支持同步，其中前三个是同步方法，一个是管道方法。
> （1）wait() / notify()方法

> （2）await() / signal()方法

> （3）BlockingQueue阻塞队列方法

> （4）PipedInputStream / PipedOutputStream

这里只介绍最常用的前三种，第四种暂不做讨论，有兴趣的读者可以自己去网上找答案。

### 一、wait() / notify()方法
wait() / nofity()方法是基类Object的两个方法，也就意味着所有Java类都会拥有这两个方法，这样，我们就可以为任何对象实现同步机制。
wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
注:Storage类中为什么要定义public void produce(int num);
和public void consume(int num);方法感到不解，为什么不直接在生产者类Producer和
消费者类Consumer中实现这两个方法，却要调用Storage类中的实现呢？
这是使用了策略模式,修改时只需要更新仓库类Storage的代码即可，生产者Producer、消费者Consumer、测试类Test的代码均不需要进行任何更改。

### 二、await() / signal()方法
在JDK5.0之后，Java提供了更加健壮的线程处理机制，包括同步、锁定、线程池等，它们可以实现更细粒度的线程控制。
await()和signal()就是其中用来做同步的两种方法，它们的功能基本上和wait() / nofity()相同，完全可以取代它们，但是它们和新引入的锁定机制Lock直接挂钩，
具有更大的灵活性。通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。

### 三、BlockingQueue阻塞队列方法
BlockingQueue是JDK5.0的新增内容，它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种await() / signal()方法。它可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法。
put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。






































