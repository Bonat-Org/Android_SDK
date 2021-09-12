# BonatSDK
## Description
Bonat easily gets you rewarded at different businesses around you.
It's the most convenient rewards app in the region, you can buy coupons and gift cards through the app and use them whenever you visit our partner businesses.
All you need is your phone to get your unique Qr code whenever you visit the shop scan it and we will count your visits until you get rewarded.

## Getting Started
Add it in your **root build.gradle** at the end of repositories:

 ```
 allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	} 
  ```
  
  Add it in your **app build.gradle** :
  ```
	dependencies {
	        implementation 'com.github.Bonat-Org:Android_SDK:1.0.1'
	}
```
## Setup SDK
first you must extend you application from **BonatApplication**. After that you need to call **initBonatSDK** function like this:
  ```kotlin
 initBonatSDK("merchantID", "secretKey", Mode.DEVELOPMENT);

```
where `<Mode>` can be `<PRODUCTION, DEVELOPMENT, STAGING>`

Now for authorize your customer you must call this function after login operation:
``` Bonat.getCustomerInfo(Context, phoneNumber)```

## Basic usage
For show and using point reward, calling **MerchantActivity**
```kotlin
  val intent = Intent(this@MainActivity, MerchantActivity::class.java)
   startActivity(intent)
   ```
 For scan QRCode and register your visit, calling **ScanQRCodeActivity**, 
 ```kotlin
  val intent = Intent(this@MainActivity, ScanQRCodeActivity::class.java)
   startActivity(intent)
   ```
note you need *camera permission*
   ``` xml
<uses-permission android:name="android.permission.CAMERA" />
   ```
![Sample](https://github.com/Bonat-Org/Android_SDK/tree/master/video/20210912-075157.gif)

## License

 ```
 Copyright 2019-2021 Bonat IT LLC 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

