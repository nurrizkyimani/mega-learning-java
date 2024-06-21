package main.designpattern;

public class BuilderPatternCreational {
    // Example fields for demonstration
    private String part1;
    private String part2;

    // Example constructor using the builder pattern
    private BuilderPatternCreational(Builder builder) {
        this.part1 = builder.part1;
        this.part2 = builder.part2;
    }

    // Static inner builder class
    public static class Builder {
        private String part1;
        private String part2;

        public Builder setPart1(String part1) {
            this.part1 = part1;
            return this;
        }

        public Builder setPart2(String part2) {
            this.part2 = part2;
            return this;
        }

        public BuilderPatternCreational build() {
            return new BuilderPatternCreational(this);
        }
    }

    // Main method
    public static void main(String[] args) {
        // Creating an instance using the builder pattern
        BuilderPatternCreational instance = new BuilderPatternCreational.Builder()
            .setPart1("Part 1 value")
            .setPart2("Part 2 value")
            .build();

        // Printing the values to demonstrate functionality
        System.out.println("Part 1: " + instance.part1);
        System.out.println("Part 2: " + instance.part2);
    }


    
}
