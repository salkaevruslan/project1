
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class test2resizeimages {
    public static String image(String filename, boolean IsSkobki) throws IOException {
        BufferedImage Image1 = ImageIO.read(images.class.getResource("" + filename));
        int[][] pixelsEx;
        int[][] pixelsEq;
        String result = "";
        pixelsEq = convert2(Image1);
        pixelsEq = resizearray(pixelsEq);
        if (IsSkobki)
            pixelsEq = postresize(pixelsEq);
        //pixelsEq = resizearray(pixelsEq);
        int[][] razdelEq = razdel(pixelsEq);
        for (int ik = 0; ik < razdelEq.length; ik++) {
            int k = 0, max = 0, resultN = -1;
            for (int b = 0; b < 16; b++) {
                String name = b + "-1.jpg";
                BufferedImage Image = ImageIO.read(images.class.getResource(name));
                pixelsEx = convert2(Image);
                pixelsEx = resizearray(pixelsEx);
                pixelsEx = resize2(pixelsEx, 1 - razdelEq[ik][0] + razdelEq[ik][1], pixelsEq.length);
                k = 0;
                for (int i = 0; i < pixelsEq.length; i++) {
                    for (int j = razdelEq[ik][0]; (j <= razdelEq[ik][1]) && (j < pixelsEx[0].length + razdelEq[ik][0]); j++) {
                        if (pixelsEx[i][j - razdelEq[ik][0]] == pixelsEq[i][j])
                            k++;

                    }
                }
                if (k > max) {
                    resultN = b;
                    max = k;
                }
            }
            if (resultN < 10)
                result += resultN;
            if (resultN == 10)
                result += "(";
            if (resultN == 11)
                result += ")";
            if (resultN == 12) {
                int imax = -1, ki = 0, imin = -1;
                for (int j = razdelEq[ik][0]; j <= razdelEq[ik][1]; j++) {
                    for (int i = 0; i < pixelsEq.length; i++) {
                        if (pixelsEq[i][j] == -3) {
                            ki = i;
                            break;
                        }
                    }
                    if (ki != 0) {
                        if ((ki < imin) || (imin == -1))
                            imin = ki;
                    }
                }
                ki = 0;
                for (int j = razdelEq[ik][0]; j <= razdelEq[ik][1]; j++) {
                    for (int i = pixelsEq.length - 1; i >= 0; i--) {
                        if (pixelsEq[i][j] == -3) {
                            ki = i;
                            break;
                        }
                    }
                    if (ki != 0) {
                        if ((ki > imax) || (imax == -1))
                            imax = ki;
                    }
                }
                if ((imax - imin) * 1.5 < razdelEq[ik][1] - razdelEq[ik][0])
                    resultN = 13;
                else
                    result += "+";

            }
            if (resultN == 13)
                result += "-";
            if (resultN == 14) {
                boolean flag = true;
                for (int i = 0; i < pixelsEq.length / 3; i++) {
                    for (int j = razdelEq[ik][0]; j <= (razdelEq[ik][0] + razdelEq[ik][1]) / 2 - 20; j++) {
                        if (pixelsEq[i][j] == -3) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        break;
                    }
                }
                if (flag) {
                    int imax = -1, ki = 0, imin = -1;
                    for (int j = razdelEq[ik][0]; j <= razdelEq[ik][1]; j++) {
                        for (int i = 0; i < pixelsEq.length; i++) {
                            if (pixelsEq[i][j] == -3) {
                                ki = i;
                                break;
                            }
                        }
                        if (ki != 0) {
                            if ((ki < imin) || (imin == -1))
                                imin = ki;
                        }
                    }
                    ki = 0;
                    for (int j = razdelEq[ik][0]; j <= razdelEq[ik][1]; j++) {
                        for (int i = pixelsEq.length - 1; i >= 0; i--) {
                            if (pixelsEq[i][j] == -3) {
                                ki = i;
                                break;
                            }
                        }
                        if (ki != 0) {
                            if ((ki > imax) || (imax == -1))
                                imax = ki;
                        }
                    }
                    if (imax - imin < razdelEq[ik][1] - razdelEq[ik][0])
                        result += "-";
                    else
                        result += "/";
                } else
                    resultN = 15;
            }
            if (resultN == 15)
                result += "*";
        }
        return result;
    }

    private static int[][] resize2(int[][] pixel, int w, int h) {
        int[][] result = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                result[i][j] = pixel[i * pixel.length / h][j * pixel[0].length / w];
            }
        }
        return result;
    }

    public static int[][] razdel(int[][] arr) {
        ArrayList<String> list = new ArrayList<String>();//😠
        String s = "";
        int minlen = 5; //минимальная длина отрезка(чтобы не брал точки)
        int point = -2;//число по которому определяем цвет
        int start = -1;
        int last = -1;
        int sold = -1;
        int lold = -1;
        for (int i = 0; i < arr[0].length; i++) {
            boolean k = false;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j][i] < point) k = true;
            }
            if (k) {
                if (start == sold) start = i;
                else last = i;
            } else {
                if (sold != start) {
                    if (last == lold) last = start;
                    s = start + " " + last;
                    if (Math.abs(last - start) >= minlen) {
                        list.add(s);
                    }
                    sold = start;
                    lold = last;
                }
            }
        }
        if (sold != start) {
            if (last == lold) s = start + " " + start;
            else s = start + " " + last;
            if (Math.abs(last - start) >= minlen) {
                list.add(s);
                // System.out.println(start + " " + last + " " + minlen);
            }
        }
        String[] sar = {}; // конвертируем ArrayList в массив
        sar = list.toArray(new String[list.size()]);
        int[][] res = new int[sar.length][2];
        for (int i = 0; i < sar.length; i++) {
            int t = sar[i].indexOf(" ");
            res[i][0] = Integer.parseInt(sar[i].substring(0, t));
            res[i][1] = Integer.parseInt(sar[i].substring(t + 1, sar[i].length()));
        }
        return res;
    }

    private static int[][] resizearray(int[][] pixel) {
        int imax = -1, imin = -1, jmax = -1, jmin = -1, ki = 0, kj = 0;
        for (int j = 0; j < pixel[0].length; j++) {
            for (int i = 0; i < pixel.length; i++) {
                if (pixel[i][j] == -3) {
                    ki = i;
                    break;
                }
            }
            if (ki != 0) {
                if ((ki < imin) || (imin == -1))
                    imin = ki;
            }
        }
        ki = 0;
        for (int j = 0; j < pixel[0].length; j++) {
            for (int i = pixel.length - 1; i >= 0; i--) {
                if (pixel[i][j] == -3) {
                    ki = i;
                    break;
                }
            }
            if (ki != 0) {
                if ((ki > imax) || (imax == -1))
                    imax = ki;
            }
        }

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[0].length; j++) {
                if (pixel[i][j] == -3) {
                    kj = j;
                    break;
                }
            }
            if (kj != 0) {
                if ((kj < jmin) || (jmin == -1))
                    jmin = kj;
            }
        }
        kj = 0;
        for (int i = 0; i < pixel.length; i++) {
            for (int j = pixel[0].length - 1; j >= 0; j--) {
                if (pixel[i][j] == -3) {
                    kj = j;
                    break;
                }
            }
            if (kj != 0) {
                if ((kj > jmax) || (jmax == -1))
                    jmax = kj;
            }
        }
        int[][] result = new int[imax - imin + 1][jmax - jmin + 1];
        for (int i = imin; i <= imax; i++) {
            for (int j = jmin; j <= jmax; j++) {
                result[i - imin][j - jmin] = pixel[i][j];
            }
        }
        return result;
    }

    private static int[][] postresize(int[][] pixel) {
        int[][] result = new int[(int) ((double) pixel.length * 2 / 3) + 2][pixel[0].length + 1];
        for (int i = (int) ((double) pixel.length / (3 * 2.76)); i <= pixel.length - ((int) ((double) (pixel.length) / 3) - (int) ((double) pixel.length / (3 * 2.76))); i++) {
            for (int j = 0; j < pixel[0].length; j++) {
                result[i - (int) ((double) pixel.length / (3 * 2.76))][j] = pixel[i][j];
            }
        }
        return result;
    }

    private static int[][] convert2(BufferedImage image) {
        int[][] result = new int[image.getHeight()][image.getWidth()];
        for (int i = 1; i < image.getHeight() - 1; i++) {
            for (int j = 1; j < image.getWidth() - 1; j++) {
                result[i][j] = image.getRGB(j, i);
                if (result[i][j] < -400000)
                    result[i][j] = -3;
                else
                    result[i][j] = -1;
            }
        }

        return result;
    }
}