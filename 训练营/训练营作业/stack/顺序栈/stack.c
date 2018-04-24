#include "stack.h"
extern int flag;
/**
*@Name..............: initStack
*@Description.....: ��ʼ��һ��ջ
*@Parameters.....: S:һ�������㣬size��ջ�Ĵ�С
*@Return values.: �����ڴ�ʧ���򷵻�OVERFLOW(-1)�����򷵻�OK(1)
*@notice.............:��
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
*@Description.....: ��һ��Ԫ��ѹ��ջ��
*@Parameters.....: S��ջ��ָ�룬e����ѹԪ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
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
*@Description.....: ��ջ��Ԫ�س�ջ
*@Parameters.....: S:ջ��ָ�룬e:ElemTypeָ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
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
*@Description.....: ��ȡջ��Ԫ��ֵ
*@Parameters.....: S��ջ��ָ�룬e��ElemTypeָ�루���Ԫ��ֵ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
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
*@Description.....: �ж��Ƿ�Ϊ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: Ϊ�շ���TRUE�����򷵻�FALSE
*/
Status isEmptyStack(SqStack *S) {
    return (-1 == S->top ? TRUE : FALSE);
}
/**
*@Name..............: destroyStack
*@Description.....: ����һ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: �ɹ��򷵻�OK��ʧ���򷵻�ERROR
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
*@Description.....: ��ӡһ��ջ
*@Parameters.....: S��ջ��ָ��
*@Return values.: ��
*/
void printStack(SqStack *S) {
    printf("ջ��Ԫ�أ�\n");
    int n = S->top;
    printf("ջ�� -- ");
    while (n != -1) {
        printf("%d -- ", S->elem[n]);
        n--;
    }
    printf("ջ��\n");
}
/**
*@Name..............: select
*@Description.....: ���в˵�ѡ��
*@Parameters.....: ��
*@Return values.: ��
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
        printf("*****************˳��ջ�Ĳ���*****************\n");
        printf("*              1-----ջ�ĳ�ʼ��              *\n");
        printf("*              2-----ѹջ����                *\n");
        printf("*              3-----��ջ����                *\n");
        printf("*              4-----��ȡջ��Ԫ��            *\n");
        printf("*              5-----�ж��Ƿ��ǿ�ջ          *\n");
        printf("*              6-----����һ��ջ              *\n");
        printf("*              7-----��ӡһ��ջ              *\n");
        printf("*              0-----�˳�����                *\n");
        printf("**********************************************\n");
        printf("������������еĲ���:");
        scanf("%d", &selectNum);
        getchar();
        //fflush(stdin);
        if (selectNum == 0) {
            break;
        }
        switch (selectNum) {
            case 1: {
                if (flag == 1) {
                    printf("ջ�Ѿ���ʼ���������ظ���ʼ����\n");
                } else {
                    printf("������ջ�Ĵ�С��");
                    scanf("%d", &size);
                    a = initStack(S, size);
                    if (a == OVERFLOW) {
                        printf("ջ��ʼ��ʧ��\n");
                    } else {
                        printf("ջ��ʼ���ɹ�\n");
                    }
                }
                break;
            }
            case 2: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else {
                    printf("�������ջ��Ԫ�أ�");
                    scanf("%d", &elem);
                    a = push(S, elem);
                    if (a == 1) {
                        printf("ѹջ�ɹ���\n");
                    } else {
                        printf("ѹջʧ��\n");
                    }
                }
                break;
            }
            case 3: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else if (isEmptyStack(S)) {
                    printf("��ջΪ��ջ��û����ջԪ�أ�\n");
                } else {
                    a = pop(S, e);
                    if (a == 1) {
                        printf("��ջ�ɹ�����ջԪ��Ϊ��%d\n", *e);
                    } else {
                        printf("��ջʧ�ܣ�\n");
                    }
                }
                break;
            }
            case 4: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������Ƚ���ջ��ʼ��������\n");
                } else if (isEmptyStack(S)) {
                    printf("��ջΪ��ջ��û��ջ��Ԫ��!\n");
                } else {
                    a = getTop(S, ele);
                    if (a == 1) {
                        printf("ջ��Ԫ��Ϊ��%d\n", *ele);
                    } else {
                        printf("��ջ��δ��ʼ�������ȳ�ʼ����\n");
                    }
                }
                break;
            }
            case 5: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�������ȳ�ʼ����\n");
                } else {
                    a = isEmptyStack(S);
                    if (a == 1) {
                        printf("��ջ�ǿ�ջ��\n");
                    } else {
                        printf("��ջ���ǿ�ջ��\n");
                    }
                }
                break;
            }
            case 6: {
                if (flag == 0) {
                    printf("ջ��δ��ʼ�����������٣�\n");
                } else {
                    a = destroyStack(S);
                    if (a == 1) {
                        printf("���ٳɹ���\n");
                    }
                }
                break;
            }
            case 7: {
                if (flag == 0) {
                    printf("ջ��û��ʼ�����޷���ӡ��\n");
                } else if (isEmptyStack(S)) {
                    printf("��ջΪ��ջ���޷���ӡ��\n");
                } else {
                    printStack(S);
                }
                break;
            }
            default: printf("��������ȷ��ֵ��\n");
                break;
        }
        system("pause");
        system("cls");
    }
}
