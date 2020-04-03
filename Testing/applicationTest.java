package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.stackApp;

class applicationTest {

	@Test
	void test_general() {
		stackApp obj = new stackApp();
		
		// sample input .
		// Note : this sample input is taken from a site . 
		String[] exp = {
				"(m/k/((f/r/q*o/i/x/c)-h/(((u-n+v*g/b)-w)-e-(l*z))-s*a)+t*j)",
				"m*k/((l/d/((v*w/(z/i+(h))/s*x))))",
				"(c*q/t/(n/v/e*f*m/h/s-(a))*((p))+((x*g)+y/(b*z*(o/(j*d/l)))/r)*w)",
				"d/x/m*b*q*n/u/r/(w/y-(o+k+z/(c)*p)/f-e/a)",
				"((g*h/((k*f/v*(z*t*l-(y*x/s)*o*(i/(m/(q)+a*w*(u*c*d)))/p*j)))))-r",
				"((((m*n*w*(o/((u/a*q)+d+f)-(p)*h*r+c*i))))/(k)+(b))+s",
				"j/((x/h*((z*a*p*f*t*(((o))/u*q)/(w*i*(r)*c/b)))/e))-d",
				"(y*j/u/(k/(w*(n*g*d*(v/o/q)*(r-h/s/f*z)+t*m))/e))",
				"(w/n*(((v/((q/a/f/j)+x*t*b*(((y)/l/p*k-d+u)/e)/m*g)/(o*r/i/c))-s))/h)-z",
				"(j/(((((z*c*p*i*(o/x-(h*g/t-a/e/b*(n-y))))*l*k+s+v)))*d*w))",
				"((y*(u*v/(h*b*e/i*j)*f)*(k))*g)",
				"((u*o*(r/y*e*(p*s/(h)*b-(j))*d+l/f)))-(i)",
				"k/r/(v*((g/(o*p*d/q*(((l+(z*n)-(h/y*w)-j+x)+(i)*a)*c+s)/m))/b)/u-e-f)",
				"u*g*i*d/b*h/((z*(e*l-c)+w)*q-(a)/v)*f",
				"a/n/e*(d*(y/b*p*(k/s/w/((m/(v/j/i)*t))))-q/g)/c*r",
				"(a/(((l*((((f*b*r*h))*n/q)-(s/o-(v)*c*t))*x)+d)/i/j))",
				"(((i/((m/(y*q*o/g/w/r-k)+u*n)+b*((x/f*((l-(a/e))/j))))))*t*d-c*h)",
				"k/(t/x/((e/j*a/d*r*m-i)*n*f-q*(h-v*c*(l+o))*g)+u*s*p)",
				"f/((t*(v/j*(c*y/(g/(l-(m*(z*i*(q*d*x)))/o)-n-p-b)/e))+k)+u*w-h*a)",
				"((i*n*p/v/j*x/(u*b*(q-r/(h/t/a/(l+(c+(e/f))-k)/z))+y/o-d/s)-g))-m",
				"d*((((z/f/r*(p*l/(e-m*((i/w*h)*k/(x/c+g))+(o)-n)))/j)))/b",
				"(b/p*((((t*(v/a*y/((e*j/m)-(s/z-r+(h-(n))))*u/i)+d)))))",
				"a/d*v*l/(e/y/x*(q/z-s)/p/j)",
				"b/a/(v/(w*g/i*q/j*(((l-c)))-(d/(y/(m)*f)+o+p)*s))/(x)-r"
		};
		//sample output .
				String[]  ans = {
						"m k / f r / q / o * i / x / c / h u n - v g * b / + w - e - l z * - / - s a * - / t j * +",
						"m k * l d / v w * z i / h + / s / x * / /",
						"c q * t / n v / e / f * m * h / s / a - / p * x g * y b z * o j d * l / / * / r / + w * +",
						"d x / m / b * q * n * u / r / w y / o k + z c / p * + f / - e a / - /",
						"g h * k f * v / z t * l * y x * s / o * i m q / a w * u c * d * * + / * p / j * - * / r -",
						"m n * w * o u a / q * d + f + / p h * r * - c i * + * k / b + s +",
						"j x h / z a * p * f * t * o u / q * * w i * r * c * b / / * e / / d -",
						"y j * u / k w n g * d * v o / q / * r h s / f / z * - * t m * + * / e / /",
						"w n / v q a / f / j / x t * b * y l / p / k * d - u + e / * m / g * + / o r * i / c / / s - * h / z -",
						"j z c * p * i * o x / h g * t / a e / b / n y - * - - * l * k * s + v + d * w * /",
						"y u v * h b * e * i / j * / f * * k * g *",
						"u o * r y / e * p s * h / b * j - * d * l f / + * i -",
						"k r / v g o p * d * q / l z n * + h y / w * - j - x + i a * + c * s + * m / / b / * u / e - f - /",
						"u g * i * d * b / h * z e l * c - * w + q * a v / - / f *",
						"a n / e / d y b / p * k s / w / m v j / i / / t * / * * q g / - * c / r *",
						"a l f b * r * h * n * q / s o / v c * t * - - * x * d + i / j / /",
						"i m y q * o * g / w / r / k - / u n * + b x f / l a e / - j / * * + / t * d * c h * -",
						"k t x / e j / a * d / r * m * i - n * f * q h v c * l o + * - * g * - / u s * p * + /",
						"f t v j / c y * g l m z i * q d * x * * * o / - / n - p - b - / e / * * k + u w * + h a * - /",
						"i n * p * v / j / x * u b * q r h t / a / l c e f / + + k - / z / / - * y o / + d s / - / g - m -",
						"d z f / r / p l * e m i w / h * k * x c / g + / * - o + n - / * j / * b /" ,
						"b p / t v a / y * e j * m / s z / r - h n - + - / u * i / * d + *",
						"a d / v * l * e y / x / q z / s - * p / j / /",
						"b a / v w g * i / q * j / l c - * d y m / f * / o + p + s * - / / x / r -"
				};
				String[] postfix = new String[24] ;
				// calculate postifx.
				for(int i =0 ; i<24;i++)
				 	postfix[i] =obj.infixToPostfix(exp[i]);
				
				assertArrayEquals(ans,postfix);
			
	}
	@Test
		void test_corners() {
		
		stackApp obj = new stackApp();

		// sample inpuy.
		String[] exp=
		{
			"a*-b+5-10",
			"-5 * -7 + 10 * a",
			"-6*10+20*30+50*40",
			"- 6 * 10 + 15*-200+300"
		};
		String [] ans = {
			"a 0 b - * 5 + 10 -",	
			"0 5 - 0 7 - * 10 a * +",
			"0 6 - 10 * 20 30 * + 50 40 * +",
			"0 6 - 10 * 15 0 200 - * + 300 +"
		};
		String[] postfix = new String[4];
		// calculate postifx.
		for(int i =0 ;i<4;i++)
			postfix[i] =obj.infixToPostfix(exp[i]);
		assertArrayEquals(ans,postfix);
	}
	@Test
	void test_evaluste() {
		stackApp obj = new stackApp();
		// sample input.
		String[] exp =
			{
				"4 8 3 * +",	
				"4 8 + 3 *",
				"6 2 / 1 2 + *",
				"8 2 / 2 2 + *",
				"46 8 4 * 2 / +",
				"2 1 4 2 1 + * 3 + + *",	
				"-100 5 * 6 + 10 -",
				"0 5 - 100 * 6 + 20 /",
				"5 100 + 0 10 - * 20 /",
				"-10 -20 * 5 / -7 6 + *"
			};
		int expected[] = {28,36,9,16,62,32,-504,-24,-52,-40}; 
		int[] my_ans = new int[10];
		for (int i =0 ;i<10;i++)
			my_ans[i] = obj.evaluate(exp[i]);
		assertArrayEquals(expected,my_ans);
	}

}
