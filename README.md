# Abluva_assignmnt
**Problem 1 :**  
<br/>To create a system like a ticket booking system. The people who need to book tickets can access this with their own separate systems. Also people can return their tickets back to the counter. All these are considered as a separate and an independent process. Our system must be good enough to manage both booking and withdrawal which are performed at same time. The system should not overlap any values or stay still and not updating the values of available tickets

Scenario 1: If there are 100 tickets and 80 are booked and 20 are returned back at the same time, then our system should display the available tickets as 40.

Scenario 2: If there are 100 tickets and 3 members entered to book tickets and seeing the count as 100 available tickets. All the three are booking each one ticket at the same time then the count should be updated as 97 for all the three systems and not as 99 on each system.

**Approach :**  
<br/>To solve this problem, a concept called concurrency plays a crucial role. Concurrency is the ability to run several programs or several parts of a program in parallel. If a time consuming task can be performed asynchronously or in parallel, this improves the throughput and the interactivity of the program.

Next synchronization, is also an important topic. Synchronization is the process that allows only one thread at a particular time to complete a given task entirely. I considered these two as the main concepts, to solve this problem and given an approach with java programming language.
