package com.atguigu.linkedList;

import javax.sound.midi.Soundbank;
import javax.xml.transform.Source;
import java.util.List;
import java.util.Stack;

/**
 * @author XiangZhang
 * @date 2020/4/11 - 22:42
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");
        HeroNode hero5 = new HeroNode(5,"测试","狮子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();


//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//
//        singleLinkedList.list();
////        System.out.println("反转之后的数组");
////        reverseList(singleLinkedList.getHead());
//
////        singleLinkedList.list();
//
//        System.out.println("逆序打印之后的链表，没有改变链表的结构");
//        reversePrint(singleLinkedList.getHead());


        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);

        HeroNode heroNode = mergeList(singleLinkedList1.getHead(), singleLinkedList2.getHead());

        HeroNode temp = heroNode.next;
        while (true){
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }


//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.addByOrder(hero3);

        /*
        singleLinkedList.list();

        HeroNode newHero = new HeroNode(2,"小卢","玉麒麟~~");
        singleLinkedList.update(newHero);


        System.out.println("修改后的节点");
        singleLinkedList.list();

        singleLinkedList.delete(1);
        singleLinkedList.delete(4);
//        singleLinkedList.delete(2);
//        singleLinkedList.delete(3);
        System.out.println("删除链表后的情况");
        singleLinkedList.list();

        //测试 求单链表中有效节点的个数
        System.out.println("有效的节点个数 = " + getLength(singleLinkedList.getHead()));

        //测试 查找单链表中的倒数第k个节点
        int index = 1;
        System.out.println("单链表中的倒数第" + index + "个节点是 ：" + findLastIndexNode(singleLinkedList.getHead(),index));
*/
    }



    //合并两个有序的单链表，合并之后的链表仍然有序
    public static HeroNode mergeList(HeroNode head1,HeroNode head2){
        if(head1.next == null){
            return head2;
        }
        if(head2.next == null){
            return head1;
        }

        HeroNode tempHead1 = head1.next;
        HeroNode tempHead2 = head2.next;
        HeroNode newHead = new HeroNode(0,"","");
        if(head1.next.no <= head2.next.no){
            newHead.next = tempHead1;
            tempHead1 = tempHead1.next;
        }else{
            newHead.next = tempHead2;
            tempHead2 = tempHead2.next;
        }

        HeroNode curNewHead = newHead.next;
        while (tempHead1 != null && tempHead2 != null){
            if(tempHead1.no <= tempHead2.no){
                curNewHead.next = tempHead1;
                tempHead1 = tempHead1.next;
            }else{
                curNewHead.next = tempHead2;
                tempHead2 = tempHead2.next;
            }
            curNewHead = curNewHead.next;
        }

        if(tempHead1 == null){
            curNewHead.next = tempHead2;
        }else if(tempHead2 == null){
            curNewHead.next = tempHead1;
        }

        return newHead;
    }


    //逆序打印链表（百度面试题）
    //方式1：先将链表进行反转操作，然后再遍历即可。但是这样做会改变链表的结构，所以不推荐这样做
    //方式2：将各个节点压入栈中，然后利用栈先进后出的特点，实现逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;
        }

        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null){
            stack.push(temp);
            temp = temp.next;
        }

        //将栈中的节点进行打印，pop出栈
        while (stack.size() > 0 ){
            System.out.println(stack.pop());
        }

    }


    //将单链表反转（腾讯面试题）
    public static void reverseList(HeroNode head){
        //链表为空，或者链表只有一个节点，不需要操作，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }

        HeroNode cur = head.next;
        HeroNode next = null;//用于记录当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null){
            next = cur.next;//保存当前节点的下一个节点
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }


    //查找单链表中的倒数第k个节点（新浪面试题）
    //思路
    //1.编写一个方法，接收head节点，同时接收一个index
    //2.index表示的倒数第index个节点
    //3.先把链表从头到尾遍历一遍，获取有效的节点个数 即getLength方法
    //4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
    //5.如果找到了，则返回该节点，反之则返回null
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if(head.next == null){//判断链表是否为空
            return null;
        }

        int size = getLength(head);//获取有效节点个数

        if(index <= 0 || index > size){//index的校验
            return null;
        }

        HeroNode temp = head.next;
        for (int i = 0; i < (size - index); i++) {
            temp = temp.next;
        }
        return temp;
    }


    //方法：获取到单链表的节点的个数（如果是带头节点的链表，需要不统计头节点）
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效的节点个数
     */
    public static int getLength(HeroNode head){
        if(head.next == null){//空链表
            return 0;
        }
        int len = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while(cur != null){
            len++;
            cur = cur.next;//后移，遍历
        }
        return len;
    }

}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //初始化一个头节点，头节点不能动，不存放具体数据
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next 指向新的节点
    public void add(HeroNode heroNode){

        //因为head节点不能动，所以我们需要一个辅助变量 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向新的节点
        temp.next = heroNode;
    }



    //第二种方式添加英雄，根据排名将英雄插入到指定的位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false

        while (true){
            if(temp.next==null){//说明temp已经到链表的最后了
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp和temp.next中间插入
                break;
            }else if(temp.next.no == heroNode.no){//说明编号重复
                flag = true;
                break;
            }
            temp = temp.next;//指针后移
        }

        if(flag){
            System.out.printf("准备插入的英雄编号%d 已经存在了，不能加入\n", heroNode.no);
        }else{
            //插入到链表中，temp的后面
            heroNode.next = temp.next;//让这个节点指向temp的下一个节点
            temp.next = heroNode;//让这个节点当作temp的下一个节点
        }


    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;

        while (true){
            if(temp==null){//说明已经是链表的最后了
                break;
            }

            if(temp.no == newHeroNode.no){//说明已经找到了，修改标志位，跳出循环
                flag = true;
                break;
            }

            temp = temp.next;//后移
        }

        if(flag){//赋值
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        }else {
            System.out.printf("没有找到编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }


    //删除节点
    //思路
    //1.head 不能动，仍然需要一个辅助变量 temp
    //2.说明 我们在比较时，是temp.next.no和需要删除的节点的nob比较
    public void delete(int no){

        HeroNode temp = head;

        boolean flag = false;

        while (true){
            if(temp.next == null){
                break;
            }

            if(temp.next.no == no){
                flag = true;
                break;
            }

            temp=temp.next;
        }

        if(flag){
            temp.next = temp.next.next;//将temp的下一个节点指向temp的下下个节点
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }

    }


    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量 temp
        HeroNode temp = head.next;
        while (true){
            if(temp == null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }

}


//定义heroNode,每个heroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;//昵称
    public HeroNode next;//指向下一个节点，初始值为null，在指针后移之后，就会有值

    //构造函数
    public HeroNode(int no,String name,String nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}