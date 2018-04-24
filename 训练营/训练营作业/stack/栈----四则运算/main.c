#include "stack.h"

int main() {
    SqStack_f S;
    char a[100], ch = 'Y';
    float result;
    while (toupper(ch) == 'Y') {
        printf("支持输入小数以及求幂,^代表幂符号\n不要出现任何空格，按回车键结束。\n");
        printf("括号一定要成对出现！\n");
        printf("请输入你的中缀表达式:\n");
        gets(a);
        initStack_f(&S, 100);
        midToPost(a);
        printf("后缀表达式为：\n");
        puts(a);
        qiuZhi(&S, a);
        getTop_f(&S, &result);
        printf("结果为(保留两位有效数字):%.2f\n", result);
        destroyStack_f(&S);
        printf("按'y'或者'Y'继续输入！按其他键则退出！\n");
        ch = getchar();
        getchar();
        fflush(stdin);
    }
    getch();
    return 0;
}
