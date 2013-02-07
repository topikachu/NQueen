package com.hp.hplab.example;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DeltaFitnessEvaluator;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.Population;
import org.jgap.event.EventManager;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;

public class NQueen {

	private static final int MAX_ALLOWED_EVOLUTIONS = 1000;
	private Logger logger = Logger.getLogger(NQueen.class);

	public int[] getSolution(int queenSize) {
		Configuration.reset();
		// This is copied from DefaultConfiguration.
		// -----------------------------------------
		Configuration config = new Configuration();		
		BestChromosomesSelector bestChromsSelector;
		int[] queen = new int[queenSize];
		try {
			bestChromsSelector = new BestChromosomesSelector(config, 1.0d);

			bestChromsSelector.setDoubletteChromosomesAllowed(false);
			config.addNaturalSelector(bestChromsSelector, true);
			config.setRandomGenerator(new StockRandomGenerator());
			config.setMinimumPopSizePercent(0);
			config.setEventManager(new EventManager());
			config.setFitnessEvaluator(new DeltaFitnessEvaluator());
			config.setChromosomePool(new ChromosomePool());
			// These are different:
			// --------------------
			config.addGeneticOperator(new GreedyCrossover(config));
			config.addGeneticOperator(new SwappingMutationOperator(config, 20));

			FitnessFunction myFunc = new NQueenFitnessFunction();

			config.setFitnessFunction(myFunc);
			config.setPopulationSize(500);

			IChromosome[] chromosomes = new IChromosome[config
					.getPopulationSize()];
			for (int pop = 0; pop < 500; pop++) {
				Gene[] sampleGenes = new Gene[queenSize];
				for (int i = 0; i < queenSize; i++) {
					sampleGenes[i] = new IntegerGene(config, 0, i);
					sampleGenes[i].setAllele(i);
				}

				chromosomes[pop] = new Chromosome(config, sampleGenes);
			}
			config.setSampleChromosome(chromosomes[0]);

			// Genotype population = Genotype.randomInitialGenotype(config);

			Genotype population = new Genotype(config, new Population(config,
					chromosomes));
			for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
				population.evolve();
				if (population.getFittestChromosome().getFitnessValue() == 0) {
					break;
				}

			}
			IChromosome bestSolutionSoFar = population.getFittestChromosome();

			for (int i = 0; i < queenSize; i++) {
				int q = (Integer) bestSolutionSoFar.getGene(i).getAllele();
				queen[i] = q;
			}
		} catch (InvalidConfigurationException e) {
			logger.error("Invalid Configuration", e);
		}
		return queen;
	}

	public void printSolution(IChromosome solution) {
		int queenSize = solution.size();
		int queen[] = new int[queenSize];
		for (int i = 0; i < queenSize; i++) {
			int q = (Integer) solution.getGene(i).getAllele();
			queen[i] = q;
		}
		System.out.println(Arrays.toString(queen) + solution.getFitnessValue());

	}

	public static class NQueenFitnessFunction extends FitnessFunction {

		@Override
		protected double evaluate(IChromosome chromosome) {
			int queenSize = chromosome.size();
			int ascendingD[] = new int[queenSize * 2]; // x+y=x+y
			int descendingD[] = new int[queenSize * 2]; // x-y=x-y
			for (int i = 0; i < queenSize; i++) {
				int q = (Integer) chromosome.getGene(i).getAllele();
				ascendingD[i + q]++;
				descendingD[i - q + queenSize]++;
			}

			return caculatePoint(ascendingD) + caculatePoint(descendingD);

		}

		private int caculatePoint(int[] p) {
			int sum = 0;
			for (int i = 0; i < p.length; i++) {
				if (p[i] >= 2) {
					sum += p[i];
				}
			}
			return sum;
		}

	}
}
