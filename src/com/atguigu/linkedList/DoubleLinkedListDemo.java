package com.atguigu.linkedList;

/**
 * @author XiangZhang
 * @date 2020/4/12 - 21:30
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表的测试");

        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//
//        doubleLinkedList.list();
//
//        //修改
//        HeroNode2 hero5 = new HeroNode2(4,"公孙胜","入云龙");
//        doubleLinkedList.update(hero5);
//        System.out.println("修改后的链表情况");
//        doubleLinkedList.list();
//
//        doubleLinkedList.delete(3);
//        System.out.println("删除后的链表情况");
//        doubleLinkedList.list();

        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();


    }
}


class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }


    //从双向链表中删除一个节点
    //说明
    //1.对于双向链表，我们可以直接找到要删除的节点，
    //2.找到以后，自我删除即可
    public void delete(int no){

        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next;

        boolean flag = false;

        while (true){
            if(temp == null){
                break;
            }

            if(temp.no == no){
                flag = true;
                break;
            }

            temp=temp.next;
        }

        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){//如果删除的节点是最后一个的话，那么他的后面一个节点是null，会造成空指针异常
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在\n",no);
        }

    }


    public void update(HeroNode2 newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;

        while (true){
            if(temp == null){//说明已经是链表的最后了
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

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){

        //因为head节点不能动，所以我们需要一个辅助变量 temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;

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
//            heroNode.next = temp.next;//让这个节点指向temp的下一个节点
//            temp.next = heroNode;//让这个节点当作temp的下一个节点
            heroNode.next = temp.next;
            if(temp.next != null){
                temp.next.pre = heroNode;
            }
            temp.next = heroNode;
            heroNode.pre = temp;
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
        HeroNode2 temp = head.next;
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


class HeroNode2{
    public int no;
    public String name;
    public String nickName;//昵称
    public HeroNode2 next;//指向下一个节点，初始值为null，在指针后移之后，就会有值
    public HeroNode2 pre;

    //构造函数
    public HeroNode2(int no,String name,String nickName){
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