#include "stack.h"

int main() {
    SqStack_f S;
    char a[100], ch = 'Y';
    float result;
    while (toupper(ch) == 'Y') {
        printf("֧������С���Լ�����,^�����ݷ���\n��Ҫ�����κοո񣬰��س���������\n");
        printf("����һ��Ҫ�ɶԳ��֣�\n");
        printf("�����������׺���ʽ:\n");
        gets(a);
        initStack_f(&S, 100);
        midToPost(a);
        printf("��׺���ʽΪ��\n");
        puts(a);
        qiuZhi(&S, a);
        getTop_f(&S, &result);
        printf("���Ϊ(������λ��Ч����):%.2f\n", result);
        destroyStack_f(&S);
        printf("��'y'����'Y'�������룡�����������˳���\n");
        ch = getchar();
        getchar();
        fflush(stdin);
    }
    getch();
    return 0;
}
