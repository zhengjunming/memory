#include "b.h"
int main() {

    Node *head;
    Node node;
    int k;
    int index1;
    int index2;
    int index3;
    int *data1;
    int *data2;
    int data;
    int i = 0;
    printf("Please define the length of the array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Please input the data:\n");
    for (; i < n; i++)
        scanf("%d", &arr[i]);
    head = create(arr, n);
    print(head);
    printf("input the recode you want to delete: ");
    scanf("%d", &index1);
    if (!(delete_a(head, index1, &data1))) {
         printf("delete successfully!\n");
    }
    else
        printf("delete fail!\n");
    print(head);
    printf("input the information you want to inserted!\n");
    printf("recode:");
    scanf("%d", &index2);
    printf("data:");
    scanf("%d", &node.data);
    if (!(insert(head, &node, index2)))
        printf("insert successfully!\n");
    else
        printf("insert fail!\n");
    print(head);
    printf("Please input the recode that you want to search(>0): ");
    scanf("%d", &data);
    k = search(head, data);
    printf("%d\n", k);
    printf("input the information you want to modify!\n");
    printf("recode: ");
    scanf("%d", &index3);
    printf("data:");
    scanf("%d", &data2);
    if (!(edit(head, index3, &data2)))
        printf("edit successfully!\n");
    else
        printf("edit fail!\n");
    printf("After sorting:\n");
    sort(head);
    destroy(head);
    getch();
    return 0;
}
