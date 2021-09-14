[![](https://jitpack.io/v/Bonat-Org/Android_SDK.svg)](https://jitpack.io/#Bonat-Org/Android_SDK)

# Bonat Mobile SDK for Android

## Copyright Statement

All rights reserved. No part of this document may be reproduced in any form or by any means or used to make any derivative such as
translation, transformation, or adaptation without the prior written permission from BONAT Corporation.

## Trademark

2019-2021 BONAT ©, all rights reserved. Contents are subject to change without prior notice.

## Contact Us

integrations@bonat.io

www.bonat.io

## About BONAT

BONAT easily gets your customers rewarded at different businesses around you.
It's the most convenient rewards app in the region, you can buy coupons and gift cards through the app and use them whenever you visit our partner businesses.
All you need is your phone to get your unique QRCode whenever you visit the shop scan it and we will count your visits until you get rewarded.

## About this Document

This document describes our BONAT Mobile SDK (for Android) and includes information on how to integrate it with the Merchant's Mobile Application.

## Intended Audience

This document was created for the Android Merchants' developers who will integrate the BONAT Mobile SDK with their Merchants' Applications.

## Supported Platforms

The first version of the solution is available only for the Android OS.

## Supported Android Platforms

The BONAT Mobile SDK supports all devices running Android 4.1.x (API level 21). _**Lollipop**_ or higher are supported.

_This release supports Android Lollipop API 21._

## Localization

The BONAT Mobile SDK supports both English and Arabic languages.

## Screen Orientation

Portrait is the only orientation supported within the BONAT Mobile SDK.

## BONAT Mobile SDK

The BONAT Mobile SDK allows Merchants to securely integrate the loyality functions. Instead of the traditional, time-consuming, and complex way of physical loyality cards, This can be used completly using a better enhanced through scanning a QRCode through BONAT SDK. This gives the Merchant/User a better and smooth user-experience using native applications.

## Integrate the BONAT Mobile SDK

To start using the **BONAT SDK**, you can proceed using the folowing instructions:

1. Add it in your **root build.gradle** at the end of repositories:

```
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add it in your **app build.gradle** :

```
	dependencies {
	        implementation 'com.github.Bonat-Org:Android_SDK:x.y.z'
	}
```

3. Extend you application from **BonatApplication**. After that you need to call **initBonatSDK** function as below:

```kotlin
initBonatSDK("merchantID", "secretKey", Mode.DEVELOPMENT);
```

where `<Mode>` can be `<PRODUCTION, STAGING>`

**Note: Merchant credentiails will be shared by BONAT team.**

4. Now for authorize your customer you must call this function after login operation:
   ` Bonat.getCustomerInfo(Context, phoneNumber)`

## OS permissions

The BONAT Mobile SDK requires the following permissions to work properly:

```xml
<uses-permission android:name="android.permission.CAMERA" />
```

## Workflow Description:

1. The Merchant’s application initiates the BONAT Mobile SDK and passes the parameters to the BONAT Mobile SDK.
2. The BONAT Mobile SDK starts a secure connection and passes the received parameters to the BONAT API to be validated.
3. The BONAT API returns the validation response.
4. The BONAT Mobile SDK submits the user data to the BONAT API to process/fetch loyality information.
5. The BONAT Mobile SDK display the loyality information.
6. Once the BONAT Mobile SDK is initialized, The Merchant can allow users to scan the QRCode in BONAT tablets to collect points.

## Call the BONAT Mobile SDK:

1. To display Bonat user points/rewards call below: **MerchantActivity**:

```kotlin
val intent = Intent(this@MainActivity, MerchantActivity::class.java)
startActivity(intent)
```

2. To scan the QRCode and register the user visit, call below: **ScanQRCodeActivity**,

```kotlin
val intent = Intent(this@MainActivity, ScanQRCodeActivity::class.java)
startActivity(intent)
```

![Sample](https://github.com/Bonat-Org/Android_SDK/tree/master/video/20210912-075157.gif)
