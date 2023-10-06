# Huffman Encoder

This project is an implementation of the Huffman Encoding algorithm in Java, a popular technique for data compression that assigns variable-length codes to characters based on their frequencies, effectively reducing the overall size of the data.

## Principles

The project is based on the principles of Huffman Encoding, a greedy algorithm that uses a variable-length code table for encoding a source symbol. The algorithm visualizes the source symbols as a binary tree with the frequency of the symbols as the weights of the nodes. The most frequent character gets the smallest code, and the least frequent character gets the largest code.

## Purpose

The purpose of this project is to demonstrate the practical application of programming concepts in real-world scenarios of data compression, such as:

Data Structures: The project utilizes various data structures like HashMap, PriorityQueue, Map, and Node to efficiently process and store information during the encoding and decoding processes.

Object-Oriented Programming: The project is structured using object-oriented programming principles with classes like HuffmanEncoder, HuffmanEncodedResult, and Node, making the code organized and reusable.

Algorithm Design: The core of the project is the Huffman encoding algorithm. The implementation demonstrates a clear understanding of the algorithm's logic and how to apply it effectively.

##Code Explanation
The project consists of the following classes:

HuffmanEncoder: The main class that performs the Huffman encoding and decoding operations. It also contains helper methods for building the lookup table and the Huffman tree.

HuffmanEncodedResult: Represents the result of the Huffman encoding process, holding the root of the Huffman tree and the encoded data.

Node: Represents a node in the Huffman tree. It contains information about the character, frequency, and left and right child nodes.

## Usage

To use this project, simply pass the string you want to compress to the **compress** method of the HuffmanEncoder class. This method will return a HuffmanEncodedResult object which contains the encoded data and the root of the Huffman tree. To decompress the data, pass the HuffmanEncodedResult object to the **decompress** method.

## Example

```java
final String test = "aaabbccccdff";
final HuffmanEncoder encoder = new HuffmanEncoder();
final HuffmanEncodedResult result = encoder.compress(test);
System.out.println(encoder.decompress(result));
This will output the original string "aaabbccccdff".
```
## Conclusion
This project was a great opportunity to apply theoretical concepts in a practical scenario. It provided a deeper understanding of Huffman Encoding, data structures, and how they can be used to solve real-world problems.
