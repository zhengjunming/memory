#include "stack.h"

/**
*@Name..............: initStack_c/_f
*@Description.....: 初始化一个栈
*@Parameters.....: S:一个链表结点，size：栈的大小
*@Return values.: 分配内存失败则返回OVERFLOW(-1)，否则返回OK(1)
*@notice.............:无
*/
Status initStack_c(SqStack_c *S, int size) {
    S->elem = (ElemType *)malloc(size * sizeof(ElemType));
    if (NULL == S->elem) {
        return OVERFLOW;
    }
    S->top = -1;
    S->size = size;
    return OK;
}
Status initStack_f(SqStack_f *S, int size) {
    S->elem = (float *)malloc(size * sizeof(float));
    if (NULL == S->elem) {
        return OVERFLOW;
    }
    S->top = -1;
    S->size = size;
    return OK;
}
/**
*@Name..............: push_c/_f
*@Description.....: 将一个元素压入栈中
*@Parameters.....: S；栈顶指针，e：待压元素
*@Return values.: 成功则返回OK，失败则返回ERROR
*/
Status push_c(SqStack_c *S, ElemType e) {
    if (NULL == S || S->top > S->size) {
        return ERROR;
    }
    S->top++;
    S->elem[S->top] = e;
    return OK;
}

Status push_f(SqStack_f *S, float e) {
    if (NULL == S || S->top > S->size) {
        return ERROR;
    }
    S->top++;
    S->elem[S->top] = e;
    return OK;
}
/**
*@Name..............: pop_c/_f
*@Description.....: 将栈顶元素出栈
*@Parameters.....: S:栈顶指针
*@Return values.: 返回栈顶元素
*/
void pop_c(SqStack_c *S, ElemType *e) {
    *e = S->elem[S->top];
    S->top--;
}
void pop_f(SqStack_f *S, float *e) {
    *e = S->elem[S->top];
    S->top--;
}
/**
*@Name..............: getTop_c/_f
*@Description.....: 获取栈顶元素值
*@Parameters.....: S：栈顶指针
*@Return values.: 返回栈顶元素
*/
void getTop_c(SqStack_c *S, ElemType *e) {
    *e = S->elem[S->top];
}
void getTop_f(SqStack_f *S, float *e) {
    *e = S->elem[S->top];
}

/**
*@Name..............: destroyStack_c/_f
*@Description.....: 销毁一个栈
*@Parameters.....: S：栈顶指针
*@Return values.: 无
*/
void destroyStack_c(SqStack_c *S) {
    free(S->elem);
}
void destroyStack_f(SqStack_f *S) {
    free(S->elem);
}
/**
*@Name..............: midToPost
*@Description.....: 将中缀表达式转换为后缀表达式
*@Parameters.....: a：字符数组
*@Return values.: 无
*/

void midToPost(char *a) {
    char b[100];
    SqStack_c S;
    int i = 0;
    int j = 0;
    char temp;
    initStack_c(&S, 100);
    push_c(&S, '(');
    while (a[i] != '\0') {
        if ((a[i] >= '0' && a[i] <= '9') || a[i] == '.') {
            b[j++] = a[i];
            if ((a[i + 1] < '0' || a[i + 1] > '9') && a[i + 1] != '.') {
                b[j++] = ' ';
            }
        } else {
            switch (a[i]) {
                case '(':
                    push_c(&S, a[i]);
                    break;
                case ')':
                    pop_c(&S, &temp);
                    while (temp != '(') {
                        b[j++] = temp;
                        pop_c(&S, &temp);
                    }
                    break;
                default:
                    getTop_c(&S, &temp);
                    while (priority(temp, a[i])) {
                        pop_c(&S, &temp);
                        b[j++] = temp;
                        getTop_c(&S, &temp);
                    }
                    push_c(&S, a[i]);
            }
        }
        i++;
    }
    pop_c(&S, &temp);
    while (temp != '(') {
        b[j++] = temp;
        pop_c(&S, &temp);
    }
    for (i = 0; i < j; i++) {
        a[i] = b[i];
    }
    a[i] = '\0';
    destroyStack_c(&S);
}
/**
*@Name..............: priority
*@Description.....: 比较运算符优先级
*@Parameters.....: a,b:字符元素
*@Return values.: 整型
*/
Status priority(char a, char b) {
    int i;
    int rec[2];
    char ch[2];
    ch[0] = a;
    ch[1] = b;
    for (i = 0; i < 2; i++) {
        switch (ch[i]) {
            case '(':
            case ')':
                rec[i] = 0;
                break;
            case '+':
            case '-':
                rec[i] = 1;
                break;
            case '/':
            case '*':
                rec[i] = 2;
                break;
            case '^':
                rec[i] = 3;
                break;
        }
    }
    if (rec[0] >= rec[1]) {
        return 1;
    } else {
        return 0;
    }
}
/**
*@Name..............: qiuZhi
*@Description.....: 后缀表达式求值
*@Parameters.....: S：栈顶指针，a：字符数组
*@Return values.: 无
*/
void qiuZhi(SqStack_f *S, char *a) {
    float num, pre, next, result;
    int i = 0;
    int j = 0;
    while (a[i] != '\0') {
        if ((a[i] >= '0' && a[i] <= '9') || a[i] == '.') {
            num = 0;
            while (a[i] != ' ' && a[i] != '.') {
                num = num * 10 + (float)(a[i++] - '0');
            }
            if (a[i] == '.') {
                j = 0;
                i++;
                while (a[i] != ' ') {
                    num = num * 10 + (float)(a[i++] - '0');
                    j++;
                }
                while (j > 0) {
                    num /= 10;
                    j--;
                }
            }
            i++;
            push_f(S, num);
            getTop_f(S, &num);
        } else {
            pop_f(S, &pre);
            pop_f(S, &next);
            switch (a[i]) {
                case '+':
                    result = next + pre;
                    break;
                case '-':
                    result = next - pre;
                    break;
                case '*':
                    result = next * pre;
                    break;
                case '/':
                    if (pre == 0) {
                        printf("除数不能为'0',按任意键退出！\n");
                        getch();
                        exit(EXIT_FAILURE);
                    } else {
                        result = next / pre;
                        break;
                    }
                case '^':
                    result = pow(next, pre);
                    break;
            }
            push_f(S, result);
            i++;
        }
    }
}
