
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanEncodedResult {
    final Node root;
    final String encodedData;

    HuffmanEncodedResult(final String encodedData, final Node root){
        this.encodedData = encodedData;
        this.root = root;
    }
    
    public String getEncodedData(){
        return this.encodedData;
    }
}

class Node implements Comparable<Node> {
    protected final char character;
    protected final int frequency;
    protected final Node leftChild;
    protected final Node rightChild;
    
    public Node (final char character, final int frequency, final Node leftChild, final Node rightChild){
        this.character = character;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    boolean isLeaf(){
        return this.leftChild == null && this.rightChild == null;
    }

    @Override
    public int compareTo(Node that) {
        final int frequencyComparison = Integer.compare(this.frequency, that.frequency);
        if(frequencyComparison != 0){
            return frequencyComparison;
        }
        return Integer.compare(this.character, that.character);
    }
    
}

public class HuffmanEncoder {
    private static final int ALPHABET_SIZE = 256;

    public HuffmanEncodedResult compress(final String data){
        final int[] freq = buildFrequencyTable(data);
        final Node root = buildHuffmanTree(freq);
        final Map<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
        
    }
    
    private static String generateEncodedData(final String data,final Map<Character, String> lookupTable){
        final StringBuilder builder = new StringBuilder();

        for(final char character : data.toCharArray()){
            builder.append(lookupTable.get(character));
        }

        return builder.toString();
    }
    
    private static Map<Character, String> buildLookupTable(final Node root){
        final Map<Character, String> lookupTable = new HashMap<>();

        buildLookupTableImpl(root, "", lookupTable);
        System.out.println(root.frequency);

        return lookupTable;
    }
    
    private static void buildLookupTableImpl(Node node, String s, Map<Character, String> lookupTable) {
        if(node.isLeaf()){
            lookupTable.put(node.character, s);
        } else {
            buildLookupTableImpl(node.leftChild, s + '0' , lookupTable);
            buildLookupTableImpl(node.rightChild, s + '1' , lookupTable);
        }
    }
    
    private static Node buildHuffmanTree(int[] freq){
        final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        for(char i = 0; i < ALPHABET_SIZE; i++){
            if(freq[i] > 0){
                priorityQueue.add(new Node(i, freq[i], null, null));
            }
        }
        
        if(priorityQueue.size() == 1){
            priorityQueue.add(new Node('\0', 1, null, null));
        }
        
        while(priorityQueue.size() > 1){
            final Node left = priorityQueue.poll();
            final Node right = priorityQueue.poll();
            final Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }
        
        return priorityQueue.poll();
    }
    
    public static int[] buildFrequencyTable(final String data){
        final int[] freq = new int[ALPHABET_SIZE];

        for(final char character: data.toCharArray()){
            freq[character]++;
        }

        return freq;
    }
    
    public String decompress(final HuffmanEncodedResult result){
        
        final StringBuilder resultBuilder = new StringBuilder();
        Node current = result.root;
        int i = 0;
        while(i < result.getEncodedData().length()){
            while(!current.isLeaf()){
                char bit = result.getEncodedData().charAt(i);
                if(bit == '1'){
                    current = current.rightChild;
                }
                else if(bit == '0'){
                    current = current.leftChild;
                }
                else{
                    throw new IllegalArgumentException("invalid code!");
                }
                i++;
            }
            resultBuilder.append(current.character);
            current = result.root;
        }
        return resultBuilder.toString();
    }
    
    public static void main(String[] args) {
        final String test = "aaabbccccdff";
        final int[] ft = buildFrequencyTable(test);
        final Node n = buildHuffmanTree(ft);
        final Map<Character, String> lookup = buildLookupTable(n);
        System.out.println(HuffmanEncoder.generateEncodedData(test, lookup));
        System.out.println(lookup);
        
        final HuffmanEncoder encoder = new HuffmanEncoder();
        final HuffmanEncodedResult result = encoder.compress(test);
        System.out.println(encoder.decompress(result));
    }
}
