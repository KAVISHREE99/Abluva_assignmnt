# Abluva_assignmnt
**Problem 1 :**  
<br/>To create a system like a ticket booking system. The people who need to book tickets can access this with their own separate systems. Also people can return their tickets back to the counter. All these are considered as a separate and an independent process. Our system must be good enough to manage both booking and withdrawal which are performed at same time. The system should not overlap any values or stay still and not updating the values of available tickets

Scenario 1: If there are 100 tickets and 80 are booked and 20 are returned back at the same time, then our system should display the available tickets as 40.

Scenario 2: If there are 100 tickets and 3 members entered to book tickets and seeing the count as 100 available tickets. All the three are booking each one ticket at the same time then the count should be updated as 97 for all the three systems and not as 99 on each system.

**Approach :**  
<br/>To solve this problem, a concept called concurrency plays a crucial role. Concurrency is the ability to run several programs or several parts of a program in parallel. If a time consuming task can be performed asynchronously or in parallel, this improves the throughput and the interactivity of the program.

Next synchronization, is also an important topic. Synchronization is the process that allows only one thread at a particular time to complete a given task entirely. I considered these two as the main concepts, to solve this problem and given an approach with java programming language.

**Problem 2 :**

To create a communication system like a message pass passing system. Here only one producer can communicate with many consumers, which means one producer can send messages to n consumers. There are three conditions we need to apply here with the problem. Firstly, if the condition given as broadcast then all the consumers who are active should receive the message from the producer. Secondly, if the condition is given as at least n, then minimum cost of n members must receive the message otherwise no one can read it. Thirdly, if the condition is given as at most of n, then maximum of n people should be active to receive the message otherwise no one can read the message.  
Incorporate these conditions with the problem and generate a solution.  
<br/>**Approach :**

To solve this problem, I used concurrency and synchronization with separate functions to perform producer role, consumer role and the three conditions. I used to create a thread for both consumer and producer. Including a concept called queue, which acts as a common storage element where the producer generates the message and add it in the queue and consumer reads the message from the queue. If the queue is full, the producer waits until there is space. A method I have inserted here is using switch case to choose the type of message based on the three conditions. Then, the broadcastMessage method sends a message to all max_cust consumers, the sendMessageAtLeastN method sends a message to at least count consumers if there are enough consumers, the sendMessageAtMostN method sends a message to at most count consumers. The consume_msg method retrieves and removes the head of the queue. It waits if the queue is empty.

I have given two different solutions with two program files.

//Msgpass2 is the first solution

//MessageSystem is the second solution

I see the second solution as updated version of first generated solution. Second program file consists of two classes as message passing and subscriber class. In MessageSystem class, I have created a list with type of subscriber class object and inserted the subscribers in to that queue.

The number of subscribers is specified during the creation of the MessageSystem class object. Each subscriber is named "Subscriber-1", "Subscriber-2", etc., for identification. The broadcast Message method sends a message to all subscribers by calling their receive Message method.

The sendMessageAtLeastN method checks if at least n subscribers are active. If the condition is met, it broadcasts the message. If not, it prints a message indicating insufficient active subscribers. The sendMessageNeverMoreThanN method checks if no more than n subscribers are active. If the condition is met, it broadcasts the message. If not, it prints a message indicating too many active subscribers.

Each Subscriber runs in its own thread. In the run method, a subscriber sleeps for a random time (up to given seconds) and then becomes inactive. The main method creates a MessageSystem with a specified number of subscribers. It starts a thread for each subscriber.

All these are done with java programming language.
