#include "sort.h"

LinkStack S;

long int MAX_SIZE;
int main()
{
    initStack(&S);
    FILE *fp;
    fp = fopen("total.txt", "wb+");
    printf("输入你想存入文件的随机数据数目：");
    int a;
    int i;
    scanf("%d", &a);
    fflush(stdin);
    srand(time(NULL));
    for (i = 0; i < a; i++) {
        fprintf(fp, "%d ", rand() % 1000);
    }

    srand(time(NULL));
    printf("请输入你想排序的数量大小：");
    scanf("%ld", &MAX_SIZE);
    //fflush(stdin);
    ElemType number[MAX_SIZE];

    // 插入排序
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    clock_t start, stop;
    double duration;
    start = clock();
    insertSort(number);
    stop = clock();
    duration = ((double)(stop - start));
    printf("插入排序：%f毫秒\n", duration);
    FILE *fp1;
    fp1 = fopen("insertSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp1, "%d ", number[i]);
    }
    fclose(fp1);

    // 快速排序
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    qSort(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("快速排序：%f毫秒\n", duration);
    FILE *fp2;
    fp2 = fopen("qSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp2, "%d ", number[i]);
    }
    fclose(fp2);

    // 归并排序
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    mergeSort(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("归并排序：%f毫秒\n", duration);
    FILE *fp3;
    fp3 = fopen("MergeSort.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp3, "%d ", number[i]);
    }
    fclose(fp3);

    // 快速排序非递归
    rewind(fp);
    for (i = 0; i < MAX_SIZE; i++) {
        fscanf(fp, "%d ", &number[i]);
    }
    start = clock();
    qSort_Recursion(number, 0, MAX_SIZE - 1);
    stop = clock();
    duration = ((double)(stop - start));
    printf("快排非递归：%f毫秒\n", duration);
    FILE *fp4;
    fp4= fopen("qSort_Recursion.txt", "w+");
    for (i = 0; i < MAX_SIZE; i++) {
        fprintf(fp4, "%d ", number[i]);
    }
    fclose(fp4);
    destroyStack(&S);

    fclose(fp);
    getch();
    return 0;
}

