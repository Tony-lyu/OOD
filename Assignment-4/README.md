# Assignment-4

Please unzip the photos for testing! our photos
are larger than the largest size hand-in server
accepts. Thank you! We have two zip files,
one is for testing transformations and the
other one contains original photos. 

For this project, we used the MVC design pattern to
realize a photo processor. Our Model contains two
part. The first part represent a pixel on an image.
We made a Pixel interface, an AbstractPixel class 
that implement it, and a RGBPixel class that 
extends the Abstract class. The other part represent
an image to process. We made an image interface and
a PPM image class that implements it. We also made 
an image factory class in case our program will be 
processing other image format in the future. 

Our View contains a ImageProcessorView interface 
and an ImageProcessorViewImpl class that 
implements it.

The Controller also has two parts, the first one
is the actual controller represented by the 
ImageProcessorController interface and the 
ImageProcessorControllerImpl class that implements
it. The other part represents commands users can
do in our controller following the command pattern.
Each command class represents a command our program
can do and will be explained in detail below. All
command classes implements ImageCommand interface. 

Pixel interface: represents a pixel on an image being
processed. 

AbstractPixel: a general pixel on an image being 
processed. It's a framework of a pixel on any 
pixel format. 

RGBPixel: represents a pixel using RGB scale. It 
contains methods that modifies the pixel. Each
pixel is modified individually when the image it
belongs to are modified. 

Image interface: represents an image being 
processed by our program. 

PPMImage: class that represents a photo in the
PPM format.

ImageFactory: parse the image file and create an
Image object according to its format. 

ImageProcessorView interface: represents text to
be displayed when user interact with the program.

ImageProcessorControllerImpl: class that implements
the view interface.

ImageProcessorController interface: represents a
controller for this program.

ImageProcessorControllerImpl: represents a 
controller implementation for this photo
processor. it interacts with the user and passes
information to model and view. 

ImageCommand interface: runs a command on a given
image. 

All commands below are accessed by the controller
and belongs to interface ImageCommand. 

Brighten: command to brighten an image by
a certain value.

Darken: command to darken an image by
a certain value.

FlipVertical: command to flip an image vertically.

FlipHorizontal: command to flip an image 
horizontally.

GreyscaleBlue: command to change an image into
greyscale using blue component.

GreyscaleRed: command to change an image into
greyscale using red component.

GreyscaleGreen: command to change an image into
greyscale using green component.

GreyscaleValue: command to change an image into
greyscale using value component.

GreyscaleIntensity: command to change an image into
greyscale using intensity component.

GreyscaleLuma: command to change an image into
greyscale using luma component.

Save: command to save an image into
a file.

script: run the main and text each line in the 
main program. don't proceed to the next line until
program output something. 

Source: 
I took the photo last year at 
isabella stewart gardner museum, I authorize 
our group to use this photo for testing. 






