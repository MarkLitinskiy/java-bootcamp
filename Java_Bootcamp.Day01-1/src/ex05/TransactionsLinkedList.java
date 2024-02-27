package ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

        private static class listNode{
            public Transaction transaction;
            public listNode listNodeNext;
            public listNode listNodePrev;

            listNode(){
                this.transaction = null;
                listNodeNext = null;
                listNodePrev = null;
            }

            listNode(Transaction transaction, listNode next, listNode prev){
                this.transaction = transaction;
                listNodeNext = next;
                listNodePrev = prev;
            }
        }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }
    listNode currentNode = new listNode();
        int countTransactions = 0;

     public void addTransaction(Transaction transaction) {
         if(currentNode.transaction == null){
             currentNode.transaction = transaction;
         } else {
            // currentNode.listNodeNext = new listNode(transaction, null, currentNode);
             listNode newNode = new listNode(transaction, null, currentNode);
             newNode.listNodePrev.listNodeNext = newNode;
             currentNode = newNode;
         }
         countTransactions++;
     }

    public void removeTransactionByID(UUID transactionID) {
        listNode temporaryNode = currentNode;
        boolean hadRemove = false;
            for (int i = 0; i < countTransactions; i++) {
                if (temporaryNode.transaction.getId() == transactionID) {
                    hadRemove = true;
                    System.out.println("Transfer To " + temporaryNode.transaction.getRecipient().getName() + "(id = " + temporaryNode.transaction.getRecipient().getId() + ") " + temporaryNode.transaction.getTransferAmount() + "removed");
                    if (temporaryNode.listNodePrev != null && temporaryNode.listNodeNext != null) {
                        temporaryNode.listNodePrev.listNodeNext = temporaryNode.listNodeNext;
                        temporaryNode.listNodeNext.listNodePrev = temporaryNode.listNodePrev;
                    } else if (temporaryNode.listNodePrev == null && temporaryNode.listNodeNext == null) {
                        currentNode = null;
                    } else if (temporaryNode.listNodePrev == null) {
                        temporaryNode.listNodeNext.listNodePrev = null;
                    } else if (temporaryNode.listNodeNext == null) {
                        temporaryNode.listNodePrev.listNodeNext = null;
                    }
                    countTransactions--;
                    break;
                }
                temporaryNode = temporaryNode.listNodePrev;
            }
            if (!hadRemove) {
                throw new TransactionNotFoundException("Идентификатора не существует!");
            }



    }

    public Transaction[] toArray(){
         Transaction[] array = new Transaction[countTransactions];
            listNode tempNode = currentNode;
         for (int i = countTransactions - 1; i >= 0; i--){
            array[i] = tempNode.transaction;
             tempNode = tempNode.listNodePrev;
         }
        return array;
    }
}
