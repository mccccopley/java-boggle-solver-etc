package etc;

/**
 * Created by mccccopley on 1/30/2017.
 */
public class PowerCompany {
    public static boolean[] initPowerArray(int[] towerArray) {
        int numCities = towerArray.length;
        boolean[] hasPower = new boolean[numCities];
        for (int cityIndex = 0; cityIndex < numCities; ++cityIndex) {
            hasPower[cityIndex] = false;
        }
        return hasPower;
    }

    public static void addPower(boolean[] powerArray, int towerIndex, int towerDistance) {
        int numCities = powerArray.length;
        int startingIndex = (towerIndex - towerDistance + 1 + numCities) % numCities;
        int endingIndex = (towerIndex + towerDistance - 1) % numCities;
        towerIndex = startingIndex;
        powerArray[towerIndex] = true;
        do {
            ++towerIndex;
            towerIndex = towerIndex % numCities;
            powerArray[towerIndex] = true;
        } while (towerIndex != endingIndex);
    }

    public static boolean allCitiesHavePower(boolean[] powerArray) {
        for (boolean hasPower : powerArray) {
            if (!hasPower) {
                return false;
            }
        }
        return true;
    }

    public static int calcMinimumTowerCount(int[] towerArray, int towerDistance) {
        boolean[] powerArray = initPowerArray(towerArray);
        for (int towerIndex = 0; towerIndex < towerArray.length; ++towerIndex) {
            if (towerArray[towerIndex] != 0) {
                addPower(powerArray, towerIndex, towerDistance);
            }
        }
        if (!allCitiesHavePower(powerArray)) {
            return -1;
        }

        // now find the minimum count...
        // strategy:
        int numCities = towerArray.length;
        int startingIndex = 0;
        int numberOfTowersUsed = 0;
        // 1. find a zero - if no zeros found, then the min can be calculated mathematically
        while (startingIndex < numCities) {
            if (towerArray[startingIndex] == 0) {
                break;
            }
            ++startingIndex;
        }
        if (startingIndex == numCities) {
            int coveragePerTower = (2 * towerDistance) - 1;
            int additionalTowers = 0;
            if (numCities % coveragePerTower != 0) {
                additionalTowers++;
            }
            return additionalTowers + (numCities / coveragePerTower);
        }
        // 2. march until there is a one
        int firstOneIndex = startingIndex;
        while (towerArray[firstOneIndex] != 1) {
            ++firstOneIndex;
            firstOneIndex = firstOneIndex % numCities;
        }
        ++numberOfTowersUsed;
        // 3. remember the furthest possible one from the current one that can provide power coverage
        while (true) {
            int furthestAllowableNextTowerIndex = (firstOneIndex + (2 * towerDistance) - 1) % numCities;
            int indexAfterFurthest = (furthestAllowableNextTowerIndex + 1) % numCities;
            // 4. march until the furthest possible index keeping track of the latest one index provided
            // 5. move up the the best one found, and then repeat until the the starting index is hit
            int nextOneIndex = -1;
            int currOneIndex = (firstOneIndex + 1) % numCities;
            while (currOneIndex != indexAfterFurthest && currOneIndex != startingIndex) {
                if (towerArray[currOneIndex] == 1) {
                    nextOneIndex = currOneIndex;
                }
                currOneIndex = (currOneIndex + 1) % numCities;
            }
            if (nextOneIndex == -1) {
                return numberOfTowersUsed;
            }
            firstOneIndex = nextOneIndex;
            // 6. keep track of the count of ones
            ++numberOfTowersUsed;
        }
    }
}
