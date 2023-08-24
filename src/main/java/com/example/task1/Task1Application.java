package com.example.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class Task1Application {
	@RestController
	public class Controller1 {
		@GetMapping("/numbertoword/{n}")
		public String getF(@PathVariable long n) {
			return EnglishNumberToWords.convert(n);
		}
	}
	public class EnglishNumberToWords {

		private static final String[] tensNames = {
				"",
				" ten",
				" twenty",
				" thirty",
				" forty",
				" fifty",
				" sixty",
				" seventy",
				" eighty",
				" ninety"
		};

		private static final String[] numNames = {
				"",
				" one",
				" two",
				" three",
				" four",
				" five",
				" six",
				" seven",
				" eight",
				" nine",
				" ten",
				" eleven",
				" twelve",
				" thirteen",
				" fourteen",
				" fifteen",
				" sixteen",
				" seventeen",
				" eighteen",
				" nineteen"
		};

		private EnglishNumberToWords() {
		}

		private static String convertLessThanOneThousand(int number) {
			String soFar;

			if (number % 100 < 20) {
				soFar = numNames[number % 100];
				number /= 100;
			} else {
				soFar = numNames[number % 10];
				number /= 10;

				soFar = tensNames[number % 10] + soFar;
				number /= 10;
			}
			if (number == 0) return soFar;
			return numNames[number] + " hundred" + soFar;
		}


		public static String convert(long number) {
			// 0 to 999 999 999 999
			if (number == 0) {
				return "zero";
			}

			String snumber = Long.toString(number);

			// pad with "0"
			String mask = "000000000000";
			DecimalFormat df = new DecimalFormat(mask);
			snumber = df.format(number);

			// XXXnnnnnnnnn
			int billions = Integer.parseInt(snumber.substring(0, 3));
			// nnnXXXnnnnnn
			int millions = Integer.parseInt(snumber.substring(3, 6));
			// nnnnnnXXXnnn
			int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
			// nnnnnnnnnXXX
			int thousands = Integer.parseInt(snumber.substring(9, 12));

			String tradBillions;
			switch (billions) {
				case 0:
					tradBillions = "";
					break;
				case 1:
					tradBillions = convertLessThanOneThousand(billions)
							+ " billion ";
					break;
				default:
					tradBillions = convertLessThanOneThousand(billions)
							+ " billion ";
			}
			String result = tradBillions;

			String tradMillions;
			switch (millions) {
				case 0:
					tradMillions = "";
					break;
				case 1:
					tradMillions = convertLessThanOneThousand(millions)
							+ " million ";
					break;
				default:
					tradMillions = convertLessThanOneThousand(millions)
							+ " million ";
			}
			result = result + tradMillions;

			String tradHundredThousands;
			switch (hundredThousands) {
				case 0:
					tradHundredThousands = "";
					break;
				case 1:
					tradHundredThousands = "one thousand ";
					break;
				default:
					tradHundredThousands = convertLessThanOneThousand(hundredThousands)
							+ " thousand ";
			}
			result = result + tradHundredThousands;

			String tradThousand;
			tradThousand = convertLessThanOneThousand(thousands);
			result = result + tradThousand;

			// remove extra spaces!
			return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		}
	}
	@RestController
	public class Controller2 {

		@GetMapping("/solve")
		public String solve(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
			double d=b*b-4*a*c;
			if(d>0){
				double x1=(-b-Math.sqrt(d))/(2*a);
				double x2=(-b+Math.sqrt(d))/(2*a);
				return("x1="+x1+" x2="+x2);
			}
			else if(d==0){
				return ("x="+(-b/(2*a)));
			}
			else return "no answer";
		}
	}
	@RestController
	public class Controller3 {

		@GetMapping("/weekday")
		public String getWeekday(@RequestParam String date) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
				Date parsedDate = dateFormat.parse(date);

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(parsedDate);

				int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

				String[] weekdays = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
				return weekdays[dayOfWeek - 1];
			} catch (ParseException e) {
				return "Ошибка в формате даты. Пожалуйста, используйте формат dd.MM.yyyy";
			}
		}
	}
	@RestController
	public class Controller4 {
		@GetMapping("/fibonacci/{n}")
		public long getF(@PathVariable long n) {
			return f(n);
		}
		public static long f(long n) {
			if(n ==0)
			{
				return 0;
			}
			else if(n ==1)
			{
				return 1;
			}
			else
			{
				return f(n - 1) + f(n - 2);
			}
		}

	}
	@RestController
	public class Controller5 {

		@GetMapping("/region/{n}")
		public String get(@PathVariable int n) {
			return getName(n);
		}

		private String getName(int n) {
			switch (n) {
				case 1: return "Республика Адыгея";
				case 2: return "Республика Башкортостан";
				case 3: return "Республика Бурятия";
				case 4: return "Республика Алтай";
				case 5: return "Республика Дагестан";
				case 6: return "Республика Ингушетия";
				case 7: return "Кабардино-Балкарская Республика";
				case 8: return "Республика Калмыкия";
				case 9: return "Карачаево-Черкесская Республика";
				case 10: return "Республика Карелия";
				case 11: return "Республика Коми";
				case 12: return "Республика Марий Эл";
				case 13: return "Республика Мордовия";
				case 14: return "Республика Саха (Якутия)";
				case 15: return "Республика Северная Осетия-Алания";
				case 16: return "Республика Татарстан";
				case 17: return "Республика Тыва";
				case 18: return "Удмуртская Республика";
				case 19: return "Республика Хакасия";
				case 20: return "Чеченская Республика";
				case 21: return "Чувашская Республика";
				case 22: return "Алтайский край";
				case 23: return "Краснодарский край";
				case 24: return "Красноярский край";
				case 25: return "Приморский край";
				case 26: return "Ставропольский край";
				case 27: return "Хабаровский край";
				case 28: return "Амурская область";
				case 29: return "Архангельская область";
				case 30: return "Астраханская область";
				case 31: return "Белгородская область";
				case 32: return "Брянская область";
				case 33: return "Владимирская область";
				case 34: return "Волгоградская область";
				case 35: return "Вологодская область";
				case 36: return "Воронежская область";
				case 37: return "Ивановская область";
				case 38: return "Иркутская область";
				case 39: return "Калининградская область";
				case 40: return "Калужская область";
				case 41: return "Камчатский край";
				case 42: return "Кемеровская область";
				case 43: return "Кировская область";
				case 44: return "Костромская область";
				case 45: return "Курганская область";
				case 46: return "Курская область";
				case 47: return "Ленинградская область";
				case 48: return "Липецкая область";
				case 49: return "Магаданская область";
				case 50: return "Московская область";
				case 51: return "Мурманская область";
				case 52: return "Нижегородская область";
				case 53: return "Новгородская область";
				case 54: return "Новосибирская область";
				case 55: return "Омская область";
				case 56: return "Оренбургская область";
				case 57: return "Орловская область";
				case 58: return "Пензенская область";
				case 59: return "Пермский край";
				case 60: return "Псковская область";
				case 61: return "Ростовская область";
				case 62: return "Рязанская область";
				case 63: return "Самарская область";
				case 64: return "Саратовская область";
				case 65: return "Сахалинская область";
				case 66: return "Свердловская область";
				case 67: return "Смоленская область";
				case 68: return "Тамбовская область";
				case 69: return "Тверская область";
				case 70: return "Томская область";
				case 71: return "Тульская область";
				case 72: return "Тюменская область";
				case 73: return "Ульяновская область";
				case 74: return "Челябинская область";
				case 75: return "Забайкальский край";
				case 76: return "Ярославская область";
				case 77: return "г. Москва";
				case 78: return "г. Санкт-Петербург";
				case 79: return "Еврейская автономная область";
				case 82: return "Республика Крым";
				case 83: return "Ненецкий автономный округ";
				case 86: return "Ханты-Мансийский автономный округ - Югра";
				case 87: return "Чукотский автономный округ";
				case 89: return "Ямало-Ненецкий автономный округ";
				case 91: return "Республика Крым";
				case 92: return "г. Севастополь";
				case 95: return "Чеченская республика";
				default: return "Регион с номером " + n + " не найден";
			}
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
	}
}
