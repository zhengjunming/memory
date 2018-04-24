#include "stack.h"

/**
*@Name..............: initStack_c/_f
*@Description.....: ��ʼ��һ��ջ
*@Parameters.....: S:һ�������㣬size��ջ�Ĵ�С
*@Return values.: �����ڴ�ʧ���򷵻�OVERFLOW(-1)�����򷵻�OK(1)
*@notice.............:��
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
*@Description.....: ��һ��Ԫ��ѹ��ջ��
*@Parameters.....: S��ջ��ָ�룬e����ѹԪ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
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
*@Description.....: ��ջ��Ԫ�س�ջ
*@Parameters.....: S:ջ��ָ��
*@Return values.: ����ջ��Ԫ��
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
*@Description.....: ��ȡջ��Ԫ��ֵ
*@Parameters.....: S��ջ��ָ��
*@Return values.: ����ջ��Ԫ��
*/
void getTop_c(SqStack_c *S, ElemType *e) {
    *e = S->elem[S->top];
}
void getTop_f(SqStack_f *S, float *e) {
    *e = S->elem[S->top];
}

/**
*@Name..............: destroyStack_c/_f
*@Description.....: ����һ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: ��
*/
void destroyStack_c(SqStack_c *S) {
    free(S->elem);
}
void destroyStack_f(SqStack_f *S) {
    free(S->elem);
}
/**
*@Name..............: midToPost
*@Description.....: ����׺���ʽת��Ϊ��׺���ʽ
*@Parameters.....: a���ַ�����
*@Return values.: ��
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
*@Description.....: �Ƚ���������ȼ�
*@Parameters.....: a,b:�ַ�Ԫ��
*@Return values.: ����
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
*@Description.....: ��׺���ʽ��ֵ
*@Parameters.....: S��ջ��ָ�룬a���ַ�����
*@Return values.: ��
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
                        printf("��������Ϊ'0',��������˳���\n");
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
