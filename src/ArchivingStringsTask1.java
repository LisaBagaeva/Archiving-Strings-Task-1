
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivingStringsTask1 {
	public static void archive(File fileIn) {
		List<String> outStrings = new ArrayList<>();
		String out = "";
		int value = 0;
		Character lastChar = 0;
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(fileIn))) {
			while ((line = br.readLine()) != null) {
				for (Character ch : line.toCharArray()) {
					if (ch != ' ') {
						if (lastChar == 0)
							lastChar = ch;
						if (lastChar == ch)
							value = value + 1;
						else {
							out = out.concat(lastChar.toString() + value);
							lastChar = ch;
							value = 1;
						}
					}
				}
				outStrings.add(out);
				out = "";
			}
			int lastIndex = outStrings.size() - 1;
			outStrings.set(lastIndex, outStrings.get(lastIndex).concat(lastChar.toString() + value));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		File fileOut = new File(fileIn.getParent(), "FileOut.txt");
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(fileOut))) {
			for (String s : outStrings)
				wr.write(s + '\n');
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void main(String[] argv) {
		ArchivingStringsTask1.archive(new File(argv[0]));
	}
}
