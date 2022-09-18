import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Internal Communication',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Bi-directional Communication'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  var message = "";
  var messageFromNative = "";
  var messageFromNative2 = "";
  var isSupported = "";
  static const platform = MethodChannel('bidirectional');

  Future<void> initNative() async {
    platform.setMethodCallHandler((call) async {
      final arg = call.arguments.toString();
      if (call.method == "fromAndroid") {
        messageFromNative = arg;
      }
      if (call.method == "fromAndroid2") {
        messageFromNative2 = arg;
      }

      //TODO: Need to add isSupported to handle the message from android
      if (call.method == "isSupported") {
        isSupported = arg;
      }
      setState(() {});
    });
  }

  Future<void> getMessage() async {
    // final ByteData bytes = await rootBundle.load('assets/ic_man.png');
    // message =
    //     await platform.invokeMethod("getMessage", bytes.buffer.asUint8List());

    var imageURL =
        "https://cdn-images-1.medium.com/max/1200/1*5-aoK8IBmXve5whBQM90GA.png";
    message = await platform.invokeMethod("getMessage", imageURL);
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    initNative();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(isSupported),
            ElevatedButton(
              onPressed: () async {
                await getMessage();
                setState(() {});
              },
              child: const Text('Get message'),
            ),
            Text(message),
            Text(messageFromNative),
            Text(messageFromNative2),
            // Image.asset(
            //   'assets/ic_man.png',
            //   width: 100,
            //   height: 100,
            // ),
          ],
        ),
      ),
    );
  }

  Widget showAlertDialog(BuildContext context) {
    return const Text(
        "Sorry! This AR feature is not supported yet on this device!");

    // Widget button = TextButton(
    //   child: const Text("Ok"),
    //   onPressed: () {
    //     Navigator.of(context).pop();
    //   },
    // );
    //
    // AlertDialog alert = AlertDialog(
    //   content: const Text(
    //       "Sorry! This AR feature is not supported yet on this device!"),
    //   actions: [
    //     button,
    //   ],
    // );
    //
    // showDialog(
    //   context: context,
    //   builder: (BuildContext context) {
    //     return alert;
    //   },
    // );
  }
}
