#include "stack.h"
extern int flag;

/**
*@Name..............: initStack
*@Description.....: 初始化一个栈
*@Parameters.....: S:一个链表结点
*@Return values.: 返回OK(表示初始化完成)
*@notice.............:无
*/
Status initStack(LinkStack *S) {
    S->top = NULL;
    S->count = 0;
    flag = 1;
    return OK;
}

/**
*@Name..............: isEmpty
*@Description.....: 判断是否为空栈
*@Parameters.....: S：栈顶指针
*@Return values.: 为空返回TRUE，否则返回FALSE
*/
Status isEmpty(LinkStack *S) {
    return (NULL == S->top ? TRUE : FALSE);
}

/**
*@Name..............: push
*@Description.....: 将一个元素压入栈中
*@Parameters.....: S；栈顶指针，e：待压元素
*@Return values.: 成功则返回OK，分配内存失败则返回OVERFLOW
*/
Status push(LinkStack *S, ElemType e) {
    StackNode *p = (StackNode *)malloc(sizeof(StackNode));
    if (NULL == p) {
        return OVERFLOW;
    }
    p->data = e;
    p->next = S->top;//将新结点*p插入链栈头部
    S->top = p;
    S->count++;
    return OK;
}

/**
*@Name..............: pop
*@Description.....: 将栈顶元素出栈
*@Parameters.....: S:栈顶指针，e:ElemType指针
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status pop(LinkStack *S, ElemType *e) {
    if (isEmpty(S))
        return ERROR;  //下溢
    StackNode *p = S->top;//保存栈顶指针
    *e = p->data;  //保存栈顶结点数据
    S->top = p->next;  //将栈顶结点从链上摘下
    free(p);
    S->count--;
    return OK;
}

/**
*@Name..............: getTop
*@Description.....: 获取栈顶元素值
*@Parameters.....: S：栈顶指针，e；ElemType指针（存放元素值）
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status getTop(LinkStack *S, ElemType *e) {
    if (isEmpty(S)) {
        return ERROR;
    }
    *e = S->top->data;
    return OK;
}

/**
*@Name..............: destroyStack
*@Description.....: 销毁一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 返回OK
*/
Status destroyStack(LinkStack *S) {
    if (isEmpty(S)) {
        free(S);
        flag = 0;
        return OK;
    }
    clear(S);
    free(S);
    flag = 0;
    return OK;
}

/**
*@Name..............: stackLength
*@Description.....: 获取栈的长度
*@Parameters.....: S：栈顶指针
*@Return values.: 返回栈的长度
*/
Status stackLength(LinkStack *S) {
    return S->count;
}

/**
*@Name..............: clear
*@Description.....: 清除一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status clear(LinkStack *S) {
    ElemType *e;
    e = (ElemType *)malloc(sizeof(ElemType));
    if (NULL == e) {
        return ERROR;
    }
    while (pop(S, e) != ERROR) {
        printf("依次清除的元素：%d\n", *e);
    }
    S->count = 0;
    return OK;
}

/**
*@Name..............: printStack
*@Description.....: 打印一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 无
*/
void printStack(LinkStack *S) {
    StackNode *p;
    p = S->top;
    printf("栈的元素为：\n");
    printf("栈顶-- ");
    while (p != NULL) {
        printf("%d -- ", p->data);
        p = p->next;
    }
    printf("栈底");
    printf("\n");
}

/**
*@Name..............: select
*@Description.....: 进行菜单选择
*@Parameters.....: 无
*@Return values.: 无
*/
void select() {
    int selectNum;
    int a;
    LinkStack Sq;
    LinkStack *S = &Sq;
    ElemType *e, *ele;
    e = (ElemType *)malloc(sizeof(ElemType));
    ele = (ElemType *)malloc(sizeof(ElemType));
    ElemType elem;
    while (1) {
        printf("*****************顺序栈的操作*****************\n");
        printf("*              1-----栈的初始化              *\n");
        printf("*              2-----压栈操作                *\n");
        printf("*              3-----出栈操作                *\n");
        printf("*              4-----获取栈顶元素            *\n");
        printf("*              5-----判断是否是空栈          *\n");
        printf("*              6-----销毁一个栈              *\n");
        printf("*              7-----打印一个栈              *\n");
        printf("*              8-----链栈的长度              *\n");
        printf("*              9-----清除一个链栈            *\n");
        printf("*              0-----退出程序                *\n");
        printf("**********************************************\n");
        printf("请输入你想进行的操作:");
        scanf("%d", &selectNum);
        getchar();
        if (selectNum == 0) {
            break;
        }
        switch (selectNum) {
            case 1: {
                if (flag == 1) {
                    printf("栈已经初始化，无需重复初始化！\n");
                } else {
                    a = initStack(S);
                    if (a == OVERFLOW) {
                        printf("栈初始化失败\n");
                    } else {
                        printf("栈初始化成功\n");
                    }
                }
                break;
            }
            case 2: {
                if (flag == 0) {
                    printf("栈还未初始化，请先进行栈初始化操作！\n");
                } else {
                    printf("请输入进栈的元素：");
                    scanf("%d", &elem);
                    a = push(S, elem);
                    if (a == 1) {
                        printf("压栈成功！\n");
                    } else {
                        printf("压栈失败\n");
                    }
                }
                break;
            }
            case 3: {
                if (flag == 0) {
                    printf("栈还未初始化，请先进行栈初始化操作！\n");
                } else if (isEmpty(S)) {
                    printf("该栈为空栈，没法出栈元素！\n");
                } else {
                    a = pop(S, e);
                    if (a == 1) {
                        printf("出栈成功！出栈元素为：%d\n", *e);
                    } else {
                        printf("出栈失败！\n");
                    }
                }
                break;
            }
            case 4: {
                if (flag == 0) {
                    printf("栈还未初始化，请先进行栈初始化操作！\n");
                } else if (isEmpty(S)) {
                    printf("该栈为空栈，没有栈顶元素!\n");
                } else {
                    a = getTop(S, ele);
                    if (a == 1) {
                        printf("栈顶元素为：%d\n", *ele);
                    } else {
                        printf("该栈还未初始化，请先初始化！\n");
                    }
                }
                break;
            }
            case 5: {
                if (flag == 0) {
                    printf("栈还未初始化，请先初始化！\n");
                } else {
                    a = isEmpty(S);
                    if (a == 1) {
                        printf("该栈是空栈！\n");
                    } else {
                        printf("该栈不是空栈！\n");
                    }
                }
                break;
            }
            case 6: {
                if (flag == 0) {
                    printf("栈还未初始化，无需销毁！\n");
                } else {
                    a = destroyStack(S);
                    if (a == 1) {
                        printf("销毁成功！\n");
                    }
                }
                break;
            }
            case 7: {
                if (flag == 0) {
                    printf("栈还没初始化，无法打印！\n");
                } else if (isEmpty(S)) {
                    printf("该栈为空栈，无法打印！\n");
                } else {
                    printStack(S);
                }
                break;
            }
            case 8: {
                if (flag == 0) {
                    printf("栈还没初始化，没有长度！\n");
                } else {
                    printf("长度为：%d\n", S->count);
                }
                break;
            }
            case 9: {
                if (flag == 0) {
                    printf("栈还未初始化，无需清除！\n");
                } else {
                    a = clear(S);
                    if (a == OK) {
                        printf("清除成功！\n");
                    }
                }
                break;
            }
            default: printf("请输入正确的值！\n");
                break;
        }
        system("pause");
        system("cls");
    }
}
