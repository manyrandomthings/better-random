package betterrandom;

import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class BetterRandom extends Random {
    private static final RandomGeneratorFactory<RandomGenerator> RANDOM_FACTORY = RandomGeneratorFactory.of(BetterRandomMod.getGeneratorType());

    private RandomGenerator internalRandom;

    public BetterRandom() {
        super(0);
        this.internalRandom = RANDOM_FACTORY.create();
    }

    public BetterRandom(long seed) {
        super(0);
        this.internalRandom = RANDOM_FACTORY.create(seed);
    }

    @Override
    public synchronized void setSeed(long seed) {
        this.internalRandom = RANDOM_FACTORY.create(seed);
    }

    @Override
    public void nextBytes(byte[] bytes) {
        this.internalRandom.nextBytes(bytes);
    }

    @Override
    public int nextInt() {
        return this.internalRandom.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return this.internalRandom.nextInt(bound);
    }

    @Override
    public long nextLong() {
        return this.internalRandom.nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return this.internalRandom.nextBoolean();
    }

    @Override
    public float nextFloat() {
        return this.internalRandom.nextFloat();
    }

    @Override
    public double nextDouble() {
        return this.internalRandom.nextDouble();
    }

    @Override
    public double nextGaussian() {
        return this.internalRandom.nextGaussian();
    }

    @Override
    public IntStream ints(long streamSize) {
        return this.internalRandom.ints(streamSize);
    }

    @Override
    public IntStream ints() {
        return this.internalRandom.ints();
    }

    @Override
    public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
        return this.internalRandom.ints(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        return this.internalRandom.ints(randomNumberOrigin, randomNumberBound);
    }

    @Override
    public LongStream longs(long streamSize) {
        return this.internalRandom.longs(streamSize);
    }

    @Override
    public LongStream longs() {
        return this.internalRandom.longs();
    }

    @Override
    public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
        return this.internalRandom.longs(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        return this.internalRandom.longs(randomNumberOrigin, randomNumberBound);
    }

    @Override
    public DoubleStream doubles(long streamSize) {
        return this.internalRandom.doubles(streamSize);
    }

    @Override
    public DoubleStream doubles() {
        return this.internalRandom.doubles();
    }

    @Override
    public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
        return this.internalRandom.doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }

    @Override
    public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        return this.internalRandom.doubles(randomNumberOrigin, randomNumberBound);
    }
}
