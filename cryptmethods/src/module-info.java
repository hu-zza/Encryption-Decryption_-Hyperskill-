module cryptmethods {
  requires cryptservice;

  provides hu.zza.hyperskill.cryptservice.CryptService with
      hu.zza.hyperskill.cryptmethods.ShiftLatin,
      hu.zza.hyperskill.cryptmethods.ShiftUnicode;
}
