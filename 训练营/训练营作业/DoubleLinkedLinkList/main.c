#include "a.h"

int main()
{
    ptr_TNode head;
    TNode node;
    node.next = node.pre = NULL;
    int index;
    int index1;
    int *data;
    int i = 0;
    int n = 0;/*结点长度*/
    printf("Please define the length of the array: ");
    scanf("%d", &n);
    int arr[n];
    printf("Please input the data:\n");
    for (; i < n; i++)
        scanf("%d", &arr[i]);
    head = create(arr, n);
    if (head == NULL) {
        printf("创建失败");
        return 0;
    }
    print(head);
    printf("input the recode you want to delete: ");
    scanf("%d", &index);
    if (!(delete_T(head, index, &data)))
        printf("delete successfully!\n");
    else
        printf("delete fail!\n");
    printf("input the information you want to inserted!\n");
    printf("recode:");
    scanf("%d", &index1);
    printf("data:");
    scanf("%d", &node.data);
    if (!(insert_T(head, &node, index1)))
        printf("insert successfully!\n");
    else
        printf("insert fail!\n");
    destroy(head);
    getch();
    return 0;
}
