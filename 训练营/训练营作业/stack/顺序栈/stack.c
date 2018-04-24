#include "stack.h"
extern int flag;
/**
*@Name..............: initStack
*@Description.....: 初始化一个栈
*@Parameters.....: S:一个链表结点，size：栈的大小
*@Return values.: 分配内存失败则返回OVERFLOW(-1)，否则返回OK(1)
*@notice.............:无
*/
Status initStack(SqStack *S, int size) {
    S->elem = (ElemType *)malloc(size * sizeof(ElemType));
    if (NULL == S->elem) {
        return OVERFLOW;
    }
    S->top = -1;
    S->size = size;
    flag = 1;
    return OK;
}

/**
*@Name..............: push
*@Description.....: 将一个元素压入栈中
*@Parameters.....: S；栈顶指针，e：待压元素
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status push(SqStack *S, ElemType e) {
    if (NULL == S || S->top > S->size) {
        return ERROR;
    }
    S->top++;
    S->elem[S->top] = e;
    return OK;
}
/**
*@Name..............: pop
*@Description.....: 将栈顶元素出栈
*@Parameters.....: S:栈顶指针，e:ElemType指针
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status pop(SqStack *S, ElemType *e) {
    if (NULL == S || S->top == -1) {
        return ERROR;
    }
    *e = S->elem[S->top];
    S->top--;
    return OK;
}
/**
*@Name..............: getTop
*@Description.....: 获取栈顶元素值
*@Parameters.....: S：栈顶指针，e；ElemType指针（存放元素值）
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status getTop(SqStack *S, ElemType *e) {
    if (NULL == S || S->top == -1) {
        return ERROR;
    } else {
        *e = S->elem[S->top];
    }
    return OK;
}
/**
*@Name..............: isEmptyStack
*@Description.....: 判断是否为空栈
*@Parameters.....: S：栈顶指针
*@Return values.: 为空返回TRUE，否则返回FALSE
*/
Status isEmptyStack(SqStack *S) {
    return (-1 == S->top ? TRUE : FALSE);
}
/**
*@Name..............: destroyStack
*@Description.....: 销毁一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status destroyStack(SqStack *S) {
    if (NULL == S) {
        return ERROR;
    }
    free(S->elem);
    flag = 0;
    return OK;
}
/**
*@Name..............: printStack
*@Description.....: 打印一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 无
*/
void printStack(SqStack *S) {
    printf("栈的元素：\n");
    int n = S->top;
    printf("栈顶 -- ");
    while (n != -1) {
        printf("%d -- ", S->elem[n]);
        n--;
    }
    printf("栈底\n");
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
    SqStack Sq;
    SqStack *S = &Sq;
    ElemType *e, *ele;
    e = (ElemType *)malloc(sizeof(ElemType));
    ele = (ElemType *)malloc(sizeof(ElemType));
    ElemType elem;
    int size;
    while (1) {
        printf("*****************顺序栈的操作*****************\n");
        printf("*              1-----栈的初始化              *\n");
        printf("*              2-----压栈操作                *\n");
        printf("*              3-----出栈操作                *\n");
        printf("*              4-----获取栈顶元素            *\n");
        printf("*              5-----判断是否是空栈          *\n");
        printf("*              6-----销毁一个栈              *\n");
        printf("*              7-----打印一个栈              *\n");
        printf("*              0-----退出程序                *\n");
        printf("**********************************************\n");
        printf("请输入你想进行的操作:");
        scanf("%d", &selectNum);
        getchar();
        //fflush(stdin);
        if (selectNum == 0) {
            break;
        }
        switch (selectNum) {
            case 1: {
                if (flag == 1) {
                    printf("栈已经初始化，无需重复初始化！\n");
                } else {
                    printf("请输入栈的大小：");
                    scanf("%d", &size);
                    a = initStack(S, size);
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
                } else if (isEmptyStack(S)) {
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
                } else if (isEmptyStack(S)) {
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
                    a = isEmptyStack(S);
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
                } else if (isEmptyStack(S)) {
                    printf("该栈为空栈，无法打印！\n");
                } else {
                    printStack(S);
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
